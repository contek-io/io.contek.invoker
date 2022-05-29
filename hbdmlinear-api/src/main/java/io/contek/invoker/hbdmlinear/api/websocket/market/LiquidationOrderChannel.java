package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.hbdmlinear.api.common._LiquidationOrder;
import io.contek.invoker.hbdmlinear.api.websocket.common.notification.NotificationWebSocketChannel;
import io.contek.invoker.hbdmlinear.api.websocket.common.notification.NotificationWebSocketChannelId;
import io.contek.invoker.hbdmlinear.api.websocket.common.notification.NotificationWebSocketDataMessage;
import io.contek.invoker.hbdmlinear.api.websocket.common.notification.NotificationWebSocketRequestIdGenerator;

import java.util.ArrayList;

import static java.lang.String.format;

public final class LiquidationOrderChannel
    extends NotificationWebSocketChannel<
        LiquidationOrderChannel.Id, LiquidationOrderChannel.Message> {

  LiquidationOrderChannel(
      LiquidationOrderChannel.Id id, NotificationWebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<LiquidationOrderChannel.Message> getMessageType() {
    return LiquidationOrderChannel.Message.class;
  }

  public static final class Id
      extends NotificationWebSocketChannelId<LiquidationOrderChannel.Message> {

    private Id(String value) {
      super(value);
    }

    public static Id of(String contractCode) {
      return new Id(format("public.%s.liquidation_orders", contractCode));
    }
  }

  public static final class Message
      extends NotificationWebSocketDataMessage<LiquidationOrderChannel.Data> {}

  public static final class Data extends ArrayList<_LiquidationOrder> {}
}
