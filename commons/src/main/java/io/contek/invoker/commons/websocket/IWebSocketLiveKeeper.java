package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface IWebSocketLiveKeeper extends IWebSocketListener {

  static IWebSocketLiveKeeper.NoOp noOp() {
    return IWebSocketLiveKeeper.NoOp.INSTANCE;
  }

  void onHeartbeat(WebSocketSession session) throws WebSocketSessionInactiveException;

  @Immutable
  final class NoOp implements IWebSocketLiveKeeper {

    private static final NoOp INSTANCE = new NoOp();

    @Override
    public void onHeartbeat(WebSocketSession session) throws WebSocketSessionInactiveException {}

    @Override
    public void onMessage(AnyWebSocketMessage message, WebSocketSession session) {}

    @Override
    public void afterDisconnect() {}

    private NoOp() {}
  }
}
