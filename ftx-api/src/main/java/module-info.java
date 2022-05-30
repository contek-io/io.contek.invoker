module io.contek.invoker.ftx.api {
  requires io.contek.invoker.commons;
  requires com.google.common;
  requires io.contek.invoker.security;
  requires io.vertx.core;
  requires org.slf4j;

  exports io.contek.invoker.ftx.api;
  exports io.contek.invoker.ftx.api.common;
  exports io.contek.invoker.ftx.api.common.constants;
  exports io.contek.invoker.ftx.api.rest;
  exports io.contek.invoker.ftx.api.rest.common;
  exports io.contek.invoker.ftx.api.rest.market;
  exports io.contek.invoker.ftx.api.rest.user;
  exports io.contek.invoker.ftx.api.websocket.common;
  exports io.contek.invoker.ftx.api.websocket.common.constants;
  exports io.contek.invoker.ftx.api.websocket.market;
  exports io.contek.invoker.ftx.api.websocket.user;
}
