package io.contek.invoker.coinbasepro.api.websocket.market;

import io.contek.invoker.coinbasepro.api.websocket.WebSocketChannel;
import io.contek.invoker.coinbasepro.api.websocket.common.WebSocketMessage;
import io.contek.invoker.coinbasepro.api.websocket.market.MatchesChannel.Message;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class MatchesChannel extends WebSocketChannel<Message> {

  private final String productId;

  MatchesChannel(String productId) {
    super("matches", productId);
    this.productId = productId;
  }

  @Override
  protected Class<Message> getMessageType() {
    return Message.class;
  }

  @Override
  protected boolean accepts(Message message) {
    return productId.equals(message.product_id);
  }

  @NotThreadSafe
  public static final class Message extends WebSocketMessage {

    public Long trade_id;
    public Long sequence;
    public String maker_order_id;
    public String taker_order_id;
    public String time;
    public String product_id;
    public Double size;
    public Double price;
    public String side;
  }
}
