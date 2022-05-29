package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.ftx.api.common._Trade;

import java.util.ArrayList;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._trades;

public final class TradesChannel
    extends WebSocketMarketChannel<TradesChannel.Id, TradesChannel.Message> {

  TradesChannel(TradesChannel.Id id) {
    super(id);
  }

  @Override
  public Class<TradesChannel.Message> getMessageType() {
    return TradesChannel.Message.class;
  }

  public static final class Id extends WebSocketMarketChannelId<TradesChannel.Message> {

    private Id(String market) {
      super(_trades, market);
    }

    public static Id of(String market) {
      return new Id(market);
    }
  }

  public static final class Data extends ArrayList<_Trade> {}

  public static final class Message extends WebSocketMarketChannelMessage<Data> {}
}
