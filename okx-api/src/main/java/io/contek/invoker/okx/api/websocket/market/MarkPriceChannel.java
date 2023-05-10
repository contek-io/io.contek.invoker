package io.contek.invoker.okx.api.websocket.market;

import io.contek.invoker.okx.api.common._MarkPrice;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketChannelKeys._mark_price;

@ThreadSafe
public final class MarkPriceChannel extends WebSocketMarketChannel<MarkPriceChannel.Message> {

  MarkPriceChannel(MarkPriceChannel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketMarketChannelId<Message> {

    private Id(String instId) {
      super(_mark_price, instId);
    }

    public static Id of(String instId) {
      return new Id(instId);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelPushData<_MarkPrice> {}
}
