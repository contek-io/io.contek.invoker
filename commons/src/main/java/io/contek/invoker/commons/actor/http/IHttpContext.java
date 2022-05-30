package io.contek.invoker.commons.actor.http;

import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.vertx.core.http.HttpClientOptions;

public sealed interface IHttpContext permits WebSocketContext, RestContext {

  String baseUrl();

  HttpClientOptions options();

}
