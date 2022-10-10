package io.contek.invoker.hbdminverse.api.websocket.market;

import io.contek.invoker.hbdminverse.api.common._LiquidationOrder;
import io.contek.invoker.hbdminverse.api.websocket.common.notification.NotificationWebSocketChannel;
import io.contek.invoker.hbdminverse.api.websocket.common.notification.NotificationWebSocketChannelId;
import io.contek.invoker.hbdminverse.api.websocket.common.notification.NotificationWebSocketDataMessage;
import io.contek.invoker.hbdminverse.api.websocket.common.notification.NotificationWebSocketRequestIdGenerator;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static java.lang.String.format;

@ThreadSafe
public final class LiquidationOrderChannel
    extends NotificationWebSocketChannel<LiquidationOrderChannel.Message, _LiquidationOrder> {

  LiquidationOrderChannel(Id id, NotificationWebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends NotificationWebSocketChannelId<Message> {

    private Id(String value) {
      super(value);
    }

    public static Id of(String contractCode) {
      return new Id(format("public.%s.liquidation_orders", contractCode));
    }
  }

  @NotThreadSafe
  public static final class Message extends NotificationWebSocketDataMessage<_LiquidationOrder> {}
}
