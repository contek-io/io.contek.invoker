package io.contek.invoker.commons.websocket;

public interface IWebSocketLiveKeeper extends IWebSocketListener {

  static IWebSocketLiveKeeper.NoOp noOp() {
    return IWebSocketLiveKeeper.NoOp.INSTANCE;
  }

  void onHeartbeat(WebSocketSession session) throws WebSocketSessionInactiveException;

  final class NoOp implements IWebSocketLiveKeeper {

    private static final NoOp INSTANCE = new NoOp();

    private NoOp() {}

    @Override
    public void onHeartbeat(WebSocketSession session) throws WebSocketSessionInactiveException {}

    @Override
    public void onMessage(AnyWebSocketMessage message, WebSocketSession session) {}

    @Override
    public void afterDisconnect() {}
  }
}
