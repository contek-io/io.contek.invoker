package io.contek.invoker.commons.actor.http;

import io.vertx.core.http.HttpClient;
import io.vertx.ext.web.client.WebClient;

public interface IHttpClient {
    record WebSocketClient(HttpClient httpClient) implements IHttpClient { }

    record RestClient(WebClient webClient) implements IHttpClient { }
}
