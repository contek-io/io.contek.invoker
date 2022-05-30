module io.contek.invoker.bybit.api {
  requires io.contek.invoker.commons;
  requires com.google.common;
  requires io.contek.invoker.security;
  requires org.slf4j;
  requires io.vertx.core;

  exports io.contek.invoker.bybit.api;
  exports io.contek.invoker.bybit.api.common;
  exports io.contek.invoker.bybit.api.common.constants;
  exports io.contek.invoker.bybit.api.rest;
  exports io.contek.invoker.bybit.api.rest.common;
  exports io.contek.invoker.bybit.api.rest.market;
  exports io.contek.invoker.bybit.api.rest.user;
  exports io.contek.invoker.bybit.api.websocket.common;
  exports io.contek.invoker.bybit.api.websocket.common.constants;
  exports io.contek.invoker.bybit.api.websocket.market;
  exports io.contek.invoker.bybit.api.websocket.user;
}
