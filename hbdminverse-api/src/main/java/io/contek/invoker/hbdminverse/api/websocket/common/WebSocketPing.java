package io.contek.invoker.hbdminverse.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketPing extends AnyWebSocketMessage {

  public long ping;
}
