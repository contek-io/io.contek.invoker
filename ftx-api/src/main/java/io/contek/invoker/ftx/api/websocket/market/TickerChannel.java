package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.ftx.api.common._Ticker;
import io.contek.invoker.ftx.api.websocket.WebSocketChannel;
import io.contek.invoker.ftx.api.websocket.WebSocketChannelId;
import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._ticker;

@ThreadSafe
public final class TickerChannel extends WebSocketChannel<TickerChannel.Id, TickerChannel.Message> {

  TickerChannel(TickerChannel.Id id) {
    super(id);
  }

  @Override
  public Class<TickerChannel.Message> getMessageType() {
    return TickerChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<TickerChannel.Message> {

    private Id(String market) {
      super(_ticker, market);
    }

    public static TickerChannel.Id of(String market) {
      return new TickerChannel.Id(market);
    }
  }

  @NotThreadSafe
  public static final class Data extends _Ticker {}

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<TickerChannel.Data> {}
}
