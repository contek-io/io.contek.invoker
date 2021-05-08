package io.contek.invoker.binancefutures.api.websocket.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancefutures.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketWebSocketApi extends BaseWebSocketApi {

  private final WebSocketContext context;
  private final WebSocketRequestIdGenerator requestIdGenerator = new WebSocketRequestIdGenerator();

  private final Map<String, AggTradeChannel> aggTradeChannels = new HashMap<>();
  private final Map<String, DepthUpdateChannel> depthUpdateChannels = new HashMap<>();
  private final Map<String, ForceOrderChannel> forceOrderChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(
        actor,
        MarketWebSocketMessageParser.getInstance(),
        IWebSocketAuthenticator.noOp(),
        IWebSocketLiveKeeper.noOp());
    this.context = context;
  }

  public AggTradeChannel getAggTradeChannel(String symbol) {
    synchronized (aggTradeChannels) {
      return aggTradeChannels.computeIfAbsent(
          symbol,
          k -> {
            AggTradeChannel result = new AggTradeChannel(k, requestIdGenerator);
            attach(result);
            return result;
          });
    }
  }

  public DepthUpdateChannel getDepthUpdateChannel(String symbol) {
    synchronized (depthUpdateChannels) {
      return depthUpdateChannels.computeIfAbsent(
          symbol,
          k -> {
            DepthUpdateChannel result = new DepthUpdateChannel(k, requestIdGenerator);
            attach(result);
            return result;
          });
    }
  }

  public ForceOrderChannel getForceOrderChannel(String symbol) {
    synchronized (forceOrderChannels) {
      return forceOrderChannels.computeIfAbsent(
          symbol,
          k -> {
            ForceOrderChannel result = new ForceOrderChannel(k, requestIdGenerator);
            attach(result);
            return result;
          });
    }
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/stream");
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ImmutableList.of();
  }

  @Override
  protected void checkErrorMessage(AnyWebSocketMessage message) throws WebSocketRuntimeException {}
}
