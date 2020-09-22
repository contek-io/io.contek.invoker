package io.contek.invoker.coinbasepro.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class WebSocketSubscriptionMessage extends WebSocketMessage {

  public List<WebSocketChannelInfo> channels;
}
