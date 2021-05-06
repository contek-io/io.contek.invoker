package io.contek.invoker.hbdminverse.api.websocket.user;

import com.google.gson.Gson;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketMessageParser;

import javax.annotation.concurrent.Immutable;

@Immutable
final class WebSocketMessageParser implements IWebSocketMessageParser {

  private final Gson gson = new Gson();

  static WebSocketMessageParser getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public AnyWebSocketMessage parse(String text) {
    throw new UnsupportedOperationException();
  }

  private WebSocketMessageParser() {}

  @Immutable
  private static final class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
