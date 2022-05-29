package io.contek.invoker.commons.websocket;

public interface IWebSocketAuthenticator extends IWebSocketListener {

  static NoOp noOp() {
    return NoOp.INSTANCE;
  }

  void handshake(WebSocketSession session);

  boolean isPending();

  boolean isCompleted();

  final class NoOp implements IWebSocketAuthenticator {

    private static final NoOp INSTANCE = new NoOp();

    private NoOp() {}

    @Override
    public void handshake(WebSocketSession session) {}

    @Override
    public boolean isPending() {
      return false;
    }

    @Override
    public boolean isCompleted() {
      return true;
    }

    @Override
    public void onMessage(AnyWebSocketMessage message, WebSocketSession session) {}

    @Override
    public void afterDisconnect() {}
  }
}
