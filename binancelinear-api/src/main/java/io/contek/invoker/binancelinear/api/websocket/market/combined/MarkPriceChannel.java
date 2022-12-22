package io.contek.invoker.binancelinear.api.websocket.market.combined;

import io.contek.invoker.binancelinear.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancelinear.api.websocket.market.MarkPriceUpdateEvent;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancelinear.api.websocket.common.constants.WebSocketChannelKeys.markPrice;

@ThreadSafe
public final class MarkPriceChannel
    extends MarketCombinedChannel<MarkPriceChannel.Message, MarkPriceUpdateEvent> {

  MarkPriceChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends MarketCombinedChannelId<Message> {

    private Id(String symbol, String interval) {
      super(markPrice(symbol, interval));
    }

    public static Id of(String symbol, String interval) {
      return new Id(symbol, interval);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketStreamMessage<MarkPriceUpdateEvent> {}
}
