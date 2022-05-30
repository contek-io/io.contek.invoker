module io.contek.invoker.binancespot.api {
  requires com.google.common;
  requires io.contek.invoker.commons;
  requires io.contek.invoker.security;
  requires org.slf4j;
  requires io.vertx.core;

  exports io.contek.invoker.binancespot.api;
  exports io.contek.invoker.binancespot.api.common;
  exports io.contek.invoker.binancespot.api.common.constants;
  exports io.contek.invoker.binancespot.api.rest;
  exports io.contek.invoker.binancespot.api.rest.common;
  exports io.contek.invoker.binancespot.api.rest.market;
  exports io.contek.invoker.binancespot.api.rest.user;
  exports io.contek.invoker.binancespot.api.websocket.common;
  exports io.contek.invoker.binancespot.api.websocket.common.constants;
  exports io.contek.invoker.binancespot.api.websocket.market;
  exports io.contek.invoker.binancespot.api.websocket.user;
}
