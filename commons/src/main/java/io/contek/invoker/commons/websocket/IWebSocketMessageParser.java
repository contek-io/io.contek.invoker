package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface IWebSocketMessageParser<Channel extends BaseWebSocketChannel<?, ?>> {

  default void register(Channel channel) {}

  default AnyWebSocketMessage parse(String text) {
    throw new UnsupportedOperationException();
  }

  default AnyWebSocketMessage parse(byte[] bytes) {
    throw new UnsupportedOperationException();
  }
}
