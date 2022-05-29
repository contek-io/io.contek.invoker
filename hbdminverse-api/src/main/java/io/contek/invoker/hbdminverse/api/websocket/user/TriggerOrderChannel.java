package io.contek.invoker.hbdminverse.api.websocket.user;

import io.contek.invoker.hbdminverse.api.websocket.common.notification.NotificationWebSocketChannel;
import io.contek.invoker.hbdminverse.api.websocket.common.notification.NotificationWebSocketChannelId;
import io.contek.invoker.hbdminverse.api.websocket.common.notification.NotificationWebSocketChannelMessage;
import io.contek.invoker.hbdminverse.api.websocket.common.notification.NotificationWebSocketRequestIdGenerator;

import static java.lang.String.format;

public final class TriggerOrderChannel
    extends NotificationWebSocketChannel<TriggerOrderChannel.Id, TriggerOrderChannel.Message> {

  TriggerOrderChannel(Id id, NotificationWebSocketRequestIdGenerator requestIdGenerator) {
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
      return new Id(format("trigger_order.%s", contractCode));
    }
  }

  public static final class Message extends NotificationWebSocketChannelMessage {}
}
