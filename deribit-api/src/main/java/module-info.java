module io.contek.invoker.deribit.api {
  requires io.contek.invoker.commons;
  requires com.google.common;
  requires io.contek.invoker.security;
  requires org.slf4j;
  requires io.vertx.core;

  exports io.contek.invoker.deribit.api;
  exports io.contek.invoker.deribit.api.common;
  exports io.contek.invoker.deribit.api.common.constants;
  exports io.contek.invoker.deribit.api.rest;
  exports io.contek.invoker.deribit.api.rest.common;
  exports io.contek.invoker.deribit.api.rest.market;
  exports io.contek.invoker.deribit.api.rest.user;
  exports io.contek.invoker.deribit.api.websocket.common;
  exports io.contek.invoker.deribit.api.websocket.common.constants;
  exports io.contek.invoker.deribit.api.websocket.market;
  exports io.contek.invoker.deribit.api.websocket.user;
}
