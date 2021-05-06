package io.contek.invoker.hbdminverse.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketPong extends AnyWebSocketMessage {

  public long pong;
}
