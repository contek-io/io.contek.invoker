package io.contek.invoker.okx.api.websocket.market;

import io.contek.invoker.okx.api.websocket.WebSocketChannelId;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelArg;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

import java.util.Objects;

public abstract class WebSocketMarketChannelId<Message extends WebSocketChannelPushData<?>>
    extends WebSocketChannelId<Message> {

  private final String instId;

  protected WebSocketMarketChannelId(String channel, String instId) {
    super(channel, instId);
    this.instId = instId;
  }

  public String getInstId() {
    return instId;
  }

  @Override
  public WebSocketChannelArg toChannelArg() {
    WebSocketChannelArg result = new WebSocketChannelArg();
    result.channel = getChannel();
    result.instId = instId;
    return result;
  }

  @Override
  public final boolean accepts(Message message) {
    WebSocketChannelArg details = message.arg;
    return Objects.equals(getChannel(), details.channel) && Objects.equals(instId, details.instId);
  }
}
