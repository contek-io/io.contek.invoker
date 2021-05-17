package io.contek.invoker.coinbasepro.api.websocket;

import io.contek.invoker.coinbasepro.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.Objects;

@Immutable
public abstract class WebSocketChannelId<Message extends WebSocketChannelMessage>
    extends BaseWebSocketChannelId<Message> {

  private final String type;
  private final String productId;

  protected WebSocketChannelId(String type, @Nullable String productId) {
    super(combine(type, productId));
    this.type = type;
    this.productId = productId;
  }

  public String getType() {
    return type;
  }

  @Nullable
  public String getProductId() {
    return productId;
  }

  @Override
  public final boolean accepts(Message message) {
    return Objects.equals(productId, message.product_id);
  }

  private static String combine(String type, @Nullable String productId) {
    if (productId == null) {
      return type;
    }
    return String.join(".", type, productId);
  }
}
