module io.contek.invoker.kraken.api {
  requires io.contek.invoker.commons;
  requires com.google.common;
  requires io.contek.invoker.security;
  requires io.vertx.core;

  exports io.contek.invoker.kraken.api;
  exports io.contek.invoker.kraken.api.common;
  exports io.contek.invoker.kraken.api.websocket.common;
  exports io.contek.invoker.kraken.api.websocket.common.constants;
  exports io.contek.invoker.kraken.api.websocket.market;
  exports io.contek.invoker.kraken.api.websocket.user;
}
