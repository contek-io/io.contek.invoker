package io.contek.invoker.hbdminverse.api.websocket.market;

import com.google.common.collect.ImmutableList;
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
  private final MarketWebSocketRequestIdGenerator requestIdGenerator =
      new MarketWebSocketRequestIdGenerator();

  private final Map<IncrementalMarketDepthChannel.Id, IncrementalMarketDepthChannel>
      incrementalMarketDepthChannels = new HashMap<>();
  private final Map<TradeDetailChannel.Id, TradeDetailChannel> tradeDetailChannels =
      new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(
        actor,
        new MarketWebSocketMessageParser(),
        IWebSocketAuthenticator.noOp(),
        WebSocketLiveKeeper.getInstance());
    this.context = context;
  }

  public IncrementalMarketDepthChannel getIncrementalMarketDepthChannel(
      IncrementalMarketDepthChannel.Id id) {
    synchronized (incrementalMarketDepthChannels) {
      return incrementalMarketDepthChannels.computeIfAbsent(
          id,
          k -> {
            IncrementalMarketDepthChannel result =
                new IncrementalMarketDepthChannel(k, requestIdGenerator);
            attach(result);
            return result;
          });
    }
  }

  public TradeDetailChannel getTradeDetailChannel(TradeDetailChannel.Id id) {
    synchronized (tradeDetailChannels) {
      return tradeDetailChannels.computeIfAbsent(
          id,
          k -> {
            TradeDetailChannel result = new TradeDetailChannel(k, requestIdGenerator);
            attach(result);
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
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/linear-swap-ws");
  }

  @Override
  protected void checkErrorMessage(AnyWebSocketMessage message) throws WebSocketRuntimeException {}
}
