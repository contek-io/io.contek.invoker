package io.contek.invoker.okx.api.websocket.market;

import io.contek.invoker.okx.api.common._Ticker;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketChannelKeys._tickers;

public final class TickersChannel
    extends WebSocketMarketChannel<TickersChannel.Id, TickersChannel.Message> {

  TickersChannel(TickersChannel.Id id) {
    super(id);
  }

  @Override
  public Class<TickersChannel.Message> getMessageType() {
    return TickersChannel.Message.class;
  }

  public static final class Id extends WebSocketMarketChannelId<TickersChannel.Message> {

    private Id(String instId) {
      super(_tickers, instId);
    }

    public static TickersChannel.Id of(String instId) {
      return new TickersChannel.Id(instId);
    }
  }

  public static final class Data extends _Ticker {}

  public static final class Message extends WebSocketChannelPushData<Data> {}
}
