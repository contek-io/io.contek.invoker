package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.hbdmlinear.api.common._TradeDetail;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class TradeDetailChannel extends WebSocketMarketChannel<TradeDetailChannel.Message> {

  private final String topic;

  TradeDetailChannel(String contractCode, WebSocketMarketRequestIdGenerator requestIdGenerator) {
    super(requestIdGenerator);
    this.topic = "market." + contractCode + ".trade.detail";
  }

  @Override
  protected String getTopic() {
    return topic;
  }

  @Override
  protected Class<Message> getMessageType() {
    return Message.class;
  }

  @Override
  protected boolean accepts(Message message) {
    return topic.equals(message.ch);
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTickMessage<_TradeDetail> {}
}
