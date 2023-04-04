package io.contek.invoker.okx.api.websocket.user;

import io.contek.invoker.okx.api.websocket.WebSocketChannelId;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelArg;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.Objects;

@Immutable
public abstract class WebSocketUserChannelId<Message extends WebSocketChannelPushData<?>>
    extends WebSocketChannelId<Message> {

  private final String type;
  private final String instId;

  protected WebSocketUserChannelId(String channel, String type, @Nullable String instId) {
    super(channel, instId == null ? type + ".*" : type + "." + instId);
    this.type = type;
    this.instId = instId;
  }

  @Nullable
  public String getInstId() {
    return instId;
  }

  @Override
  public WebSocketChannelArg toChannelArg() {
    WebSocketChannelArg result = new WebSocketChannelArg();
    result.channel = getChannel();
    result.instType = type;
    result.instId = instId;
    return result;
  }

  @Override
  public final boolean accepts(Message message) {
    WebSocketChannelArg details = message.arg;
    return Objects.equals(getChannel(), details.channel) && Objects.equals(instId, details.instId);
  }
}
