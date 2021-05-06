package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.hbdmlinear.api.common._Depth;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketSubscribeIncrementalMarketDepthRequest;
import io.contek.invoker.hbdmlinear.api.websocket.common.constants.WebSocketDataTypeKeys;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBING;
import static java.lang.String.format;

@ThreadSafe
public final class IncrementalMarketDepthChannel
    extends WebSocketMarketChannel<IncrementalMarketDepthChannel.Message> {

  IncrementalMarketDepthChannel(
      String contractCode, int size, WebSocketMarketRequestIdGenerator requestIdGenerator) {
    super(
        format("market.%s.depth.size_%d.high_freq", contractCode, size),
        Message.class,
        requestIdGenerator);
  }

  @Override
  protected SubscriptionState subscribe(WebSocketSession session) {
    WebSocketSubscribeIncrementalMarketDepthRequest request =
        new WebSocketSubscribeIncrementalMarketDepthRequest();
    request.sub = getTopic();
    request.data_type = WebSocketDataTypeKeys._incremental;
    request.id = generateNextId();
    session.send(request);
    return SUBSCRIBING;
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTickMessage<Tick> {}

  @NotThreadSafe
  public static final class Tick extends _Depth {

    public String event;
  }
}
