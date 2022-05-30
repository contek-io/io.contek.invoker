module io.contek.invoker.bitstamp.api {
  requires io.contek.invoker.commons;
  requires com.google.common;
  requires io.contek.invoker.security;
  requires io.vertx.core;

  exports io.contek.invoker.bitstamp.api;
  exports io.contek.invoker.bitstamp.api.common;
  exports io.contek.invoker.bitstamp.api.rest;
  exports io.contek.invoker.bitstamp.api.rest.market;
  exports io.contek.invoker.bitstamp.api.rest.user;
  exports io.contek.invoker.bitstamp.api.websocket.common;
  exports io.contek.invoker.bitstamp.api.websocket.common.constants;
  exports io.contek.invoker.bitstamp.api.websocket.market;
  exports io.contek.invoker.bitstamp.api.websocket.user;
}
