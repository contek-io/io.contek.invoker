module io.contek.invoker.binancefutures.api {
  requires com.google.common;
  requires io.contek.invoker.commons;
  requires io.contek.invoker.security;
  requires org.slf4j;
  requires io.vertx.core;

  exports io.contek.invoker.binancefutures.api;
  exports io.contek.invoker.binancefutures.api.common;
  exports io.contek.invoker.binancefutures.api.common.constants;
  exports io.contek.invoker.binancefutures.api.rest;
  exports io.contek.invoker.binancefutures.api.rest.common;
  exports io.contek.invoker.binancefutures.api.rest.market;
  exports io.contek.invoker.binancefutures.api.rest.user;
  exports io.contek.invoker.binancefutures.api.websocket.common;
  exports io.contek.invoker.binancefutures.api.websocket.common.constants;
  exports io.contek.invoker.binancefutures.api.websocket.market;
  exports io.contek.invoker.binancefutures.api.websocket.user;
}
