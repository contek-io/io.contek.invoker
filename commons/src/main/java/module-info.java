module io.contek.invoker.commons {
    requires com.google.gson;
    requires okhttp3;
    requires io.contek.invoker.security;
    requires com.google.common;
    requires org.slf4j;
    requires okio;
    exports io.contek.ursa.cache;
    exports io.contek.invoker.ursa.core.api;
    exports io.contek.invoker.commons.websocket;
    exports io.contek.invoker.commons.websocket.constants;
    exports io.contek.invoker.commons.rest;
    exports io.contek.invoker.commons.actor;
    exports io.contek.invoker.commons.actor.http;
    exports io.contek.invoker.commons.actor.ratelimit;
    exports io.contek.invoker.commons;
}