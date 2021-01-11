package io.contek.invoker.deribit.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketPing extends WebSocketOutboundMessage {

  public Integer rtt;
}
