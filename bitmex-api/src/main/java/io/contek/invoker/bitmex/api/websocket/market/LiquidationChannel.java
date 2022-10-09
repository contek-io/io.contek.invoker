package io.contek.invoker.bitmex.api.websocket.market;

import io.contek.invoker.bitmex.api.common._LiquidationOrder;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannel;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannelId;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static java.lang.String.format;

@ThreadSafe
public final class LiquidationChannel
    extends WebSocketChannel<LiquidationChannel.Message, _LiquidationOrder> {

  LiquidationChannel(LiquidationChannel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<LiquidationChannel.Message> {

    private final String instrument;

    private Id(String instrument) {
      super(format("liquidation:%s", instrument));
      this.instrument = instrument;
    }

    public static Id of(String instrument) {
      return new Id(instrument);
    }

    @Override
    public boolean accepts(LiquidationChannel.Message message) {
      return message.data.stream().map(order -> order.symbol).anyMatch(instrument::equals);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTableDataMessage<_LiquidationOrder> {}
}
