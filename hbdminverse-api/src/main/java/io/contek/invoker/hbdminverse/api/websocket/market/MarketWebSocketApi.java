package io.contek.invoker.hbdminverse.api.websocket.market;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Table;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketLiveKeeper;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

import static io.contek.invoker.hbdminverse.api.ApiFactory.RateLimits.ONE_IP_WEB_SOCKET_CONNECTION_REQUEST;

@ThreadSafe
public final class MarketWebSocketApi extends BaseWebSocketApi {

  private final WebSocketContext context;
  private final WebSocketMarketRequestIdGenerator requestIdGenerator =
      new WebSocketMarketRequestIdGenerator();

  private final Table<String, Integer, IncrementalMarketDepthChannel>
      integerIncrementalMarketDepthChannels = HashBasedTable.create();
  private final Map<String, TradeDetailChannel> tradeDetailChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(
        actor,
        new WebSocketMarketMessageParser(),
        IWebSocketAuthenticator.noOp(),
        WebSocketLiveKeeper.getInstance());
    this.context = context;
  }

  public IncrementalMarketDepthChannel getIncrementalMarketDepthChannel(
      String contractCode, int size) {
    synchronized (integerIncrementalMarketDepthChannels) {
      IncrementalMarketDepthChannel result =
          integerIncrementalMarketDepthChannels.get(contractCode, size);
      if (result == null) {
        result = new IncrementalMarketDepthChannel(contractCode, size, requestIdGenerator);
        init(result);
        integerIncrementalMarketDepthChannels.put(contractCode, size, result);
      }
      return result;
    }
  }

  public TradeDetailChannel getTradeDetailChannel(String contractCode) {
    synchronized (tradeDetailChannels) {
      return tradeDetailChannels.computeIfAbsent(
          contractCode,
          k -> {
            TradeDetailChannel result = new TradeDetailChannel(k, requestIdGenerator);
            init(result);
            return result;
          });
    }
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_IP_WEB_SOCKET_CONNECTION_REQUEST;
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/swap-ws");
  }

  @Override
  protected void checkErrorMessage(AnyWebSocketMessage message) {}

  private void init(WebSocketMarketChannel<?> channel) {
    WebSocketMarketMessageParser parser = (WebSocketMarketMessageParser) getParser();
    channel.register(parser);
    attach(channel);
  }
}
