package io.contek.invoker.coinbasepro.api.websocket.market;

import io.contek.invoker.coinbasepro.api.websocket.WebSocketChannel;
import io.contek.invoker.coinbasepro.api.websocket.WebSocketChannelId;
import io.contek.invoker.coinbasepro.api.websocket.common.WebSocketChannelMessage;

import java.util.ArrayList;
import java.util.List;

public final class Level2Channel extends WebSocketChannel<Level2Channel.Id, Level2Channel.Message> {

  Level2Channel(Level2Channel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  public static final class Id extends WebSocketChannelId<Level2Channel.Message> {

    private Id(String productId) {
      super("level2_50", productId);
    }

    public static Id of(String productId) {
      return new Id(productId);
    }
  }

  public abstract static class Message extends WebSocketChannelMessage {}

  public static final class L2UpdateMessage extends Message {

    public String time;
    public List<L2UpdateChange> changes;

    @Override
    public String toString() {
      return "L2UpdateMessage{" +
              "time='" + time + '\'' +
              ", changes=" + changes +
              '}';
    }
  }

  public static final class L2UpdateChange extends ArrayList<String> {}

  public static final class SnapshotMessage extends Message {

    public List<SnapshotLevel> bids;
    public List<SnapshotLevel> asks;

    @Override
    public String toString() {
      return "SnapshotMessage{" +
              "bids=" + bids +
              ", asks=" + asks +
              '}';
    }
  }

  public static final class SnapshotLevel extends ArrayList<Double> {}
}
