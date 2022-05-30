module io.contek.invoker.bybitlinear.api {
  requires io.contek.invoker.commons;
  requires com.google.common;
  requires io.contek.invoker.security;
  requires org.slf4j;
  requires io.vertx.core;

  exports io.contek.invoker.bybitlinear.api;
  exports io.contek.invoker.bybitlinear.api.common;
  exports io.contek.invoker.bybitlinear.api.common.constants;
  exports io.contek.invoker.bybitlinear.api.rest;
  exports io.contek.invoker.bybitlinear.api.rest.common;
  exports io.contek.invoker.bybitlinear.api.rest.market;
  exports io.contek.invoker.bybitlinear.api.rest.user;
  exports io.contek.invoker.bybitlinear.api.websocket.common;
  exports io.contek.invoker.bybitlinear.api.websocket.common.constants;
  exports io.contek.invoker.bybitlinear.api.websocket.market;
  exports io.contek.invoker.bybitlinear.api.websocket.user;
}
