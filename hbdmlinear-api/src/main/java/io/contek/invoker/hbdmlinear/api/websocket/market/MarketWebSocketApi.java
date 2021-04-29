package io.contek.invoker.hbdmlinear.api.websocket.market;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Table;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

import static io.contek.invoker.hbdmlinear.api.ApiFactory.RateLimits.ONE_IP_WEB_SOCKET_CONNECTION_REQUEST;

@ThreadSafe
public final class MarketWebSocketApi extends BaseWebSocketApi {

  private final WebSocketContext context;

  private final Table<String, Integer, IncrementalMarketDepthChannel>
      integerIncrementalMarketDepthChannels = HashBasedTable.create();
  private final Map<String, TradeDetailChannel> tradeDetailChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, new WebSocketMarketMessageParser(), IWebSocketAuthenticator.noOp());
    this.context = context;
  }

  public IncrementalMarketDepthChannel getIncrementalMarketDepthChannel(
      String contractCode, int size) {
    synchronized (integerIncrementalMarketDepthChannels) {
      IncrementalMarketDepthChannel channel =
          integerIncrementalMarketDepthChannels.get(contractCode, size);
      if (channel == null) {
        channel = new IncrementalMarketDepthChannel(contractCode, size);
        integerIncrementalMarketDepthChannels.put(contractCode, size, channel);
      }
      return channel;
    }
  }

  public TradeDetailChannel getTradeDetailChannel(String contractCode) {
    synchronized (tradeDetailChannels) {
      return tradeDetailChannels.computeIfAbsent(contractCode, TradeDetailChannel::new);
    }
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_IP_WEB_SOCKET_CONNECTION_REQUEST;
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/linear-swap-ws");
  }

  @Override
  protected void checkErrorMessage(AnyWebSocketMessage message) {}
}
