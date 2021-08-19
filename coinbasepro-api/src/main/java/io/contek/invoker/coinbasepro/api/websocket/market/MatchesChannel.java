package io.contek.invoker.coinbasepro.api.websocket.market;

import io.contek.invoker.coinbasepro.api.websocket.WebSocketChannel;
import io.contek.invoker.coinbasepro.api.websocket.WebSocketChannelId;
import io.contek.invoker.coinbasepro.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class MatchesChannel
    extends WebSocketChannel<MatchesChannel.Id, MatchesChannel.Message> {

  MatchesChannel(MatchesChannel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<MatchesChannel.Message> {

    private Id(String productId) {
      super("matches", productId);
    }

    public static Id of(String productId) {
      return new Id(productId);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage {

    public Long trade_id;
    public Long sequence;
    public String maker_order_id;
    public String taker_order_id;
    public String time;
    public Double size;
    public Double price;
    public String side;

    @Override
    public String toString() {
      return "Message{" +
              "trade_id=" + trade_id +
              ", sequence=" + sequence +
              ", maker_order_id='" + maker_order_id + '\'' +
              ", taker_order_id='" + taker_order_id + '\'' +
              ", time='" + time + '\'' +
              ", size=" + size +
              ", price=" + price +
              ", side='" + side + '\'' +
              '}';
    }
  }
}
