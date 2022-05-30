package io.contek.invoker.commons.websocket;

import io.contek.invoker.commons.actor.http.IHttpClient;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.http.WebSocket;
import io.vertx.core.http.WebsocketVersion;

import java.util.List;

public interface WebSocketCall {

  static WebSocketCall fromUrl(String url) {
    return client -> {
      if (client instanceof IHttpClient.WebSocketClient webSocketClient) {
        return webSocketClient.httpClient().webSocketAbs(url, MultiMap.caseInsensitiveMultiMap(), WebsocketVersion.V13, List.of());
      }

      throw new RuntimeException("Can't submit websocket call from RestClient");
    };
  }

  Future<WebSocket> submit(IHttpClient client);
}
