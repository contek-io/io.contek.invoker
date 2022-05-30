package io.contek.invoker.binancedelivery.api.websocket.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancedelivery.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.security.ICredential;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MarketWebSocketApi extends BaseWebSocketApi {

  private final WebSocketContext context;
  private final WebSocketRequestIdGenerator requestIdGenerator = new WebSocketRequestIdGenerator();

  private final Map<AggTradeChannel.Id, AggTradeChannel> aggTradeChannels =
      new ConcurrentHashMap<>();
  private final Map<DepthUpdateChannel.Id, DepthUpdateChannel> depthUpdateChannels =
      new ConcurrentHashMap<>();
  private final Map<ForceOrderChannel.Id, ForceOrderChannel> forceOrderChannels =
      new ConcurrentHashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(
        actor,
        MarketWebSocketMessageParser.getInstance(),
        IWebSocketAuthenticator.noOp(),
        IWebSocketLiveKeeper.noOp());
    this.context = context;
  }

  public AggTradeChannel getAggTradeChannel(AggTradeChannel.Id id) {
    return aggTradeChannels.computeIfAbsent(
        id,
        k -> {
          AggTradeChannel result = new AggTradeChannel(k, requestIdGenerator);
          attach(result);
          return result;
        });
  }

  public DepthUpdateChannel getDepthUpdateChannel(DepthUpdateChannel.Id id) {
    return depthUpdateChannels.computeIfAbsent(
        id,
        k -> {
          DepthUpdateChannel result = new DepthUpdateChannel(k, requestIdGenerator);
          attach(result);
          return result;
        });
  }

  public ForceOrderChannel getForceOrderChannel(ForceOrderChannel.Id id) {
    return forceOrderChannels.computeIfAbsent(
        id,
        k -> {
          ForceOrderChannel result = new ForceOrderChannel(k, requestIdGenerator);
          attach(result);
          return result;
        });
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.baseUrl() + "/stream");
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ImmutableList.of();
  }

  @Override
  protected void checkErrorMessage(AnyWebSocketMessage message) throws WebSocketRuntimeException {}
}
