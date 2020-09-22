package io.contek.invoker.coinbasepro.api.websocket.market;

import io.contek.invoker.coinbasepro.api.websocket.WebSocketChannel;
import io.contek.invoker.coinbasepro.api.websocket.common.WebSocketMessage;
import io.contek.invoker.coinbasepro.api.websocket.market.Level2Channel.Message;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public final class Level2Channel extends WebSocketChannel<Message> {

  private final String productId;

  Level2Channel(String productId) {
    super("level2", productId);
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
  public abstract static class Message extends WebSocketMessage {

    public String product_id;
  }

  @NotThreadSafe
  public static final class L2UpdateMessage extends Message {

    public String time;
    public List<L2UpdateChange> changes;
  }

  @NotThreadSafe
  public static final class L2UpdateChange extends ArrayList<String> {}

  @NotThreadSafe
  public static final class SnapshotMessage extends Message {

    public List<SnapshotLevel> bids;
    public List<SnapshotLevel> asks;
  }

  @NotThreadSafe
  public static final class SnapshotLevel extends ArrayList<Double> {}
}
