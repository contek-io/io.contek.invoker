package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.hbdmlinear.api.common._Depth;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class MarketDepthChannel extends WebSocketMarketChannel<MarketDepthChannel.Message> {

  private final String topic;

  MarketDepthChannel(String contractCode, String type, WebSocketMarketRequestIdGenerator requestIdGenerator) {
    super(requestIdGenerator);
    this.topic = String.format("market.%s.depth.%s", contractCode, type);
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
  public static final class Message extends WebSocketTickMessage<_Depth> {}
}
