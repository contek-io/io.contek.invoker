module io.contek.invoker.commons {
  requires com.google.gson;
  requires io.contek.invoker.security;
  requires com.google.common;
  requires org.slf4j;
  requires io.vertx.core;
  requires io.vertx.web.client;
  requires com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.annotation;
  requires com.fasterxml.jackson.core;
  requires io.netty.buffer;

  exports io.contek.ursa.cache;
  exports io.contek.invoker.commons.buffer;
  exports io.contek.invoker.ursa.core.api;
  exports io.contek.invoker.commons.websocket;
  exports io.contek.invoker.commons.websocket.constants;
  exports io.contek.invoker.commons.rest;
  exports io.contek.invoker.commons.actor;
  exports io.contek.invoker.commons.actor.http;
  exports io.contek.invoker.commons.actor.ratelimit;
  exports io.contek.invoker.commons;
}
