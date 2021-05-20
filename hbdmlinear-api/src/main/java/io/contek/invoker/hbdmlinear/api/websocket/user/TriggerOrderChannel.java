package io.contek.invoker.hbdmlinear.api.websocket.user;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static java.lang.String.format;

@ThreadSafe
public final class TriggerOrderChannel
    extends UserWebSocketChannel<TriggerOrderChannel.Id, TriggerOrderChannel.Message> {

  TriggerOrderChannel(
      TriggerOrderChannel.Id id, UserWebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<TriggerOrderChannel.Message> getMessageType() {
    return TriggerOrderChannel.Message.class;
  }

  @Immutable
  public static final class Id extends UserWebSocketChannelId<TriggerOrderChannel.Message> {

    private Id(String value) {
      super(value);
    }

    public static Id of(String contractCode) {
      return new Id(format("trigger_order.%s", contractCode));
    }
  }

  @NotThreadSafe
  public static final class Message extends UserWebSocketChannelMessage {}
}
