module io.contek.invoker.hbdmlinear.api {
  requires io.contek.invoker.commons;
  requires com.google.common;
  requires io.contek.invoker.security;
  requires com.google.gson;
  requires io.vertx.core;

  exports io.contek.invoker.hbdmlinear.api;
  exports io.contek.invoker.hbdmlinear.api.common;
  exports io.contek.invoker.hbdmlinear.api.common.constants;
  exports io.contek.invoker.hbdmlinear.api.rest;
  exports io.contek.invoker.hbdmlinear.api.rest.common;
  exports io.contek.invoker.hbdmlinear.api.rest.market;
  exports io.contek.invoker.hbdmlinear.api.rest.user;
  exports io.contek.invoker.hbdmlinear.api.websocket.common;
  exports io.contek.invoker.hbdmlinear.api.websocket.common.constants;
  exports io.contek.invoker.hbdmlinear.api.websocket.market;
  exports io.contek.invoker.hbdmlinear.api.websocket.user;
}
