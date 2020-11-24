package io.contek.invoker.binancedelivery.api.websocket.market;

import io.contek.invoker.binancedelivery.api.websocket.WebSocketApi;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.security.ICredential;
import io.contek.invoker.commons.api.websocket.WebSocketCall;
import io.contek.invoker.commons.api.websocket.WebSocketContext;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketWebSocketApi extends WebSocketApi {

  private final WebSocketContext context;

  private final Map<String, AggTradeChannel> aggTradeChannels = new HashMap<>();
  private final Map<String, DepthUpdateChannel> depthUpdateChannels = new HashMap<>();
  private final Map<String, ForceOrderChannel> forceOrderChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor);
    this.context = context;
  }

  public AggTradeChannel getAggTradeChannel(String symbol) {
    synchronized (aggTradeChannels) {
      return aggTradeChannels.computeIfAbsent(
          symbol,
          k -> {
            AggTradeChannel result = new AggTradeChannel(k, getRequestIdGenerator());
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
            DepthUpdateChannel result = new DepthUpdateChannel(k, getRequestIdGenerator());
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
            ForceOrderChannel result = new ForceOrderChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/stream");
  }
}
