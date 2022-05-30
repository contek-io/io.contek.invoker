module io.contek.invoker.coinbasepro.api {
  requires io.contek.invoker.commons;
  requires com.google.common;
  requires io.contek.invoker.security;
  requires io.vertx.core;

  exports io.contek.invoker.coinbasepro.api;
  exports io.contek.invoker.coinbasepro.api.common.constants;
  exports io.contek.invoker.coinbasepro.api.rest.market;
  exports io.contek.invoker.coinbasepro.api.rest.user;
  exports io.contek.invoker.coinbasepro.api.websocket.common;
  exports io.contek.invoker.coinbasepro.api.websocket.common.constants;
  exports io.contek.invoker.coinbasepro.api.websocket.market;
  exports io.contek.invoker.coinbasepro.api.websocket.user;
}
