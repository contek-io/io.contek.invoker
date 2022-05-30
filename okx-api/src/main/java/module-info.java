module io.contek.invoker.okx.api {
  requires io.contek.invoker.commons;
  requires io.contek.invoker.security;
  requires com.google.common;
  requires com.google.gson;
  requires org.slf4j;
  requires io.vertx.core;

  exports io.contek.invoker.okx.api;
  exports io.contek.invoker.okx.api.common;
  exports io.contek.invoker.okx.api.common.constants;
  exports io.contek.invoker.okx.api.rest;
  exports io.contek.invoker.okx.api.rest.common;
  exports io.contek.invoker.okx.api.rest.market;
  exports io.contek.invoker.okx.api.rest.user;
  exports io.contek.invoker.okx.api.websocket.common;
  exports io.contek.invoker.okx.api.websocket.common.constants;
  exports io.contek.invoker.okx.api.websocket.market;
  exports io.contek.invoker.okx.api.websocket.user;
}
