package io.contek.invoker.hbdminverse.api.websocket.market;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class MarketWebSocketChannelMessage extends AnyWebSocketMessage {

  public String ch;
}
