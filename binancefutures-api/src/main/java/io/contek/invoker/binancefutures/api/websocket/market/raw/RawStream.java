package io.contek.invoker.binancefutures.api.websocket.market.raw;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventData;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
abstract class RawStream<Data extends WebSocketEventData> extends BaseWebSocketApi {

  private final MarketWebSocketRawChannelId<Data> id;
  private final WebSocketContext context;
  private final MarketWebSocketRawChannel<Data> channel;

  private final AtomicBoolean attached = new AtomicBoolean(false);

  RawStream(MarketWebSocketRawChannelId<Data> id, IActor actor, WebSocketContext context) {
    super(
        actor,
        new RawStreamMessageParser<>(id.getType()),
        IWebSocketAuthenticator.noOp(),
        IWebSocketLiveKeeper.noOp());
    this.id = id;
    this.context = context;
    channel = new MarketWebSocketRawChannel<>(id);
  }

  @Override
  protected final ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ImmutableList.of();
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/ws/" + id.getValue());
  }

  @Override
  protected void checkErrorMessage(AnyWebSocketMessage message) throws WebSocketRuntimeException {}

  MarketWebSocketRawChannel<Data> getChannel() {
    if (!attached.get()) {
      attach(channel);
    }
    return channel;
  }
}
