package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.ftx.api.common._Ticker;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._ticker;

public final class TickerChannel
    extends WebSocketMarketChannel<TickerChannel.Id, TickerChannel.Message> {

  TickerChannel(TickerChannel.Id id) {
    super(id);
  }

  @Override
  public Class<TickerChannel.Message> getMessageType() {
    return TickerChannel.Message.class;
  }

  public static final class Id extends WebSocketMarketChannelId<TickerChannel.Message> {

    private Id(String market) {
      super(_ticker, market);
    }

    public static TickerChannel.Id of(String market) {
      return new TickerChannel.Id(market);
    }
  }

  public static final class Data extends _Ticker {}

  public static final class Message extends WebSocketMarketChannelMessage<Data> {}
}
