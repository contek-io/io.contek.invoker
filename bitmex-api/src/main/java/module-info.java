module io.contek.invoker.bitmex.api {
  requires io.contek.invoker.commons;
  requires com.google.common;
  requires io.contek.invoker.security;
  requires com.google.gson;
  requires org.slf4j;
  requires io.vertx.core;

  exports io.contek.invoker.bitmex.api;
  exports io.contek.invoker.bitmex.api.common;
  exports io.contek.invoker.bitmex.api.common.constants;
  exports io.contek.invoker.bitmex.api.rest;
  exports io.contek.invoker.bitmex.api.rest.market;
  exports io.contek.invoker.bitmex.api.rest.user;
  exports io.contek.invoker.bitmex.api.websocket.common;
  exports io.contek.invoker.bitmex.api.websocket.common.constants;
  exports io.contek.invoker.bitmex.api.websocket.market;
  exports io.contek.invoker.bitmex.api.websocket.user;
}
