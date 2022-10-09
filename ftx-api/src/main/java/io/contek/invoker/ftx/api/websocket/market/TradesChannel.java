package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.ftx.api.common._Trade;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._trades;

@ThreadSafe
public final class TradesChannel
    extends WebSocketMarketChannel<TradesChannel.Message, List<_Trade>> {

  TradesChannel(TradesChannel.Id id) {
    super(id);
  }

  @Override
  public Class<TradesChannel.Message> getMessageType() {
    return TradesChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketMarketChannelId<TradesChannel.Message> {

    private Id(String market) {
      super(_trades, market);
    }

    public static Id of(String market) {
      return new Id(market);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketMarketChannelMessage<List<_Trade>> {}
}
