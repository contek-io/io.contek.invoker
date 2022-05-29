package io.contek.invoker.hbdminverse.api.websocket.market;

import io.contek.invoker.hbdminverse.api.common._LiquidationOrder;
import io.contek.invoker.hbdminverse.api.websocket.common.notification.NotificationWebSocketChannel;
import io.contek.invoker.hbdminverse.api.websocket.common.notification.NotificationWebSocketChannelId;
import io.contek.invoker.hbdminverse.api.websocket.common.notification.NotificationWebSocketDataMessage;
import io.contek.invoker.hbdminverse.api.websocket.common.notification.NotificationWebSocketRequestIdGenerator;

import java.util.ArrayList;

import static java.lang.String.format;

public final class LiquidationOrderChannel
    extends NotificationWebSocketChannel<
        LiquidationOrderChannel.Id, LiquidationOrderChannel.Message> {

  LiquidationOrderChannel(Id id, NotificationWebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  public static final class Id extends NotificationWebSocketChannelId<Message> {

    private Id(String value) {
      super(value);
    }

    public static Id of(String contractCode) {
      return new Id(format("public.%s.liquidation_orders", contractCode));
    }
  }

  public static final class Message extends NotificationWebSocketDataMessage<Data> {}

  public static final class Data extends ArrayList<_LiquidationOrder> {}
}
