package io.contek.invoker.coinbasepro.api.websocket.common;

import java.util.List;

public final class WebSocketSubscriptionMessage extends WebSocketMessage {

  public List<WebSocketChannelInfo> channels;
}
