package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.ftx.api.common._Trade;
import io.contek.invoker.ftx.api.websocket.WebSocketChannel;
import io.contek.invoker.ftx.api.websocket.WebSocketChannelId;
import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._trades;

@ThreadSafe
public final class TradesChannel extends WebSocketChannel<TradesChannel.Id, TradesChannel.Message> {

  TradesChannel(TradesChannel.Id id) {
    super(id);
  }

  @Override
  protected Class<TradesChannel.Message> getMessageType() {
    return TradesChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId {

    private Id(String market) {
      super(_trades, market);
    }

    public static Id of(String market) {
      return new Id(market);
    }
  }

  @NotThreadSafe
  public static final class Data extends ArrayList<_Trade> {}

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<TradesChannel.Data> {}
}
