package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.hbdmlinear.api.common._Depth;
import io.contek.invoker.hbdmlinear.api.websocket.common.constants.WebSocketDataTypeKeys;
import io.contek.invoker.hbdmlinear.api.websocket.common.marketdata.*;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBING;
import static java.lang.String.format;

@ThreadSafe
public final class IncrementalDepthChannel
    extends MarketDataMarketWebSocketChannel<
        IncrementalDepthChannel.Id, IncrementalDepthChannel.Message> {

  IncrementalDepthChannel(
      IncrementalDepthChannel.Id id, MarketDataWebSocketRequestIdGenerator requestIdGenerator) {
    super(id, Message.class, requestIdGenerator);
  }

  @Override
  protected SubscriptionState subscribe(WebSocketSession session) {
    IncrementalDepthChannel.Id id = getId();
    SubscribeIncrementalMarketDepthRequest request = new SubscribeIncrementalMarketDepthRequest();
    request.sub = id.getChannel();
    request.data_type = WebSocketDataTypeKeys._incremental;
    request.id = generateNexRequestId();
    session.send(request);
    return SUBSCRIBING;
  }

  @Immutable
  public static final class Id extends MarketDataWebSocketChannelId<Message> {

    private Id(String topic) {
      super(topic);
    }

    public static Id of(String contractCode, int size) {
      return new Id(format("market.%s.depth.size_%d.high_freq", contractCode, size));
    }
  }

  @NotThreadSafe
  public static final class Message extends MarketDataWebSocketTickMessage<Data> {}

  @NotThreadSafe
  public static final class Data extends _Depth {

    public String event;
  }

  @NotThreadSafe
  public static final class SubscribeIncrementalMarketDepthRequest
      extends MarketDataWebSocketSubscribeRequest {

    public String data_type;
  }

  @Immutable
  public static final class SizeKeys {

    public static final int _20_unmerged_data = 20;
    public static final int _150_unmerged_data = 150;

    private SizeKeys() {}
  }
}
