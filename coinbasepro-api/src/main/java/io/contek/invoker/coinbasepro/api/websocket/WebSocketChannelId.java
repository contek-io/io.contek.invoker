package io.contek.invoker.coinbasepro.api.websocket;

import io.contek.invoker.coinbasepro.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import java.util.Objects;

public abstract class WebSocketChannelId<Message extends WebSocketChannelMessage>
    extends BaseWebSocketChannelId<Message> {

  private final String channel;
  private final String productId;

  protected WebSocketChannelId(String channel, String productId) {
    super(combine(channel, productId));
    this.channel = channel;
    this.productId = productId;
  }

  private static String combine(String channel, String productId) {
    if (productId == null) {
      return channel;
    }
    return String.join(".", channel, productId);
  }

  public String getChannel() {
    return channel;
  }

  public String getProductId() {
    return productId;
  }

  @Override
  public final boolean accepts(Message message) {
    return Objects.equals(productId, message.product_id);
  }
}
