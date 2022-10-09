package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.ftx.api.common._Ticker;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._ticker;

@ThreadSafe
public final class TickerChannel extends WebSocketMarketChannel<TickerChannel.Message, _Ticker> {

  TickerChannel(TickerChannel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketMarketChannelId<Message> {

    private Id(String market) {
      super(_ticker, market);
    }

    public static Id of(String market) {
      return new Id(market);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketMarketChannelMessage<_Ticker> {}
}
