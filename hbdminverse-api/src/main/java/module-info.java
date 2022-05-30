module io.contek.invoker.hbdminverse.api {
  requires io.contek.invoker.commons;
  requires com.google.common;
  requires io.contek.invoker.security;
  requires io.vertx.core;

  exports io.contek.invoker.hbdminverse.api;
  exports io.contek.invoker.hbdminverse.api.common;
  exports io.contek.invoker.hbdminverse.api.common.constants;
  exports io.contek.invoker.hbdminverse.api.rest;
  exports io.contek.invoker.hbdminverse.api.rest.common;
  exports io.contek.invoker.hbdminverse.api.rest.market;
  exports io.contek.invoker.hbdminverse.api.rest.user;
  exports io.contek.invoker.hbdminverse.api.websocket.common;
  exports io.contek.invoker.hbdminverse.api.websocket.common.constants;
  exports io.contek.invoker.hbdminverse.api.websocket.market;
  exports io.contek.invoker.hbdminverse.api.websocket.user;
}
