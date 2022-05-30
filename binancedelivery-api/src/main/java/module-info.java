module io.contek.invoker.binancedelivery.api {
  exports io.contek.invoker.binancedelivery.api;
  exports io.contek.invoker.binancedelivery.api.common;
  exports io.contek.invoker.binancedelivery.api.common.constants;
  exports io.contek.invoker.binancedelivery.api.rest;
  exports io.contek.invoker.binancedelivery.api.rest.common;
  exports io.contek.invoker.binancedelivery.api.rest.market;
  exports io.contek.invoker.binancedelivery.api.rest.user;
  exports io.contek.invoker.binancedelivery.api.websocket.common;
  exports io.contek.invoker.binancedelivery.api.websocket.common.constants;
  exports io.contek.invoker.binancedelivery.api.websocket.market;
  exports io.contek.invoker.binancedelivery.api.websocket.user;

  requires com.google.common;
  requires io.contek.invoker.commons;
  requires io.contek.invoker.security;
  requires org.slf4j;
  requires io.vertx.core;
}
