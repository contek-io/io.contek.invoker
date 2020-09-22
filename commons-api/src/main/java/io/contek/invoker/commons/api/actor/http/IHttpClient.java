package io.contek.invoker.commons.api.actor.http;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import javax.annotation.concurrent.ThreadSafe;
import java.net.InetAddress;

@ThreadSafe
public interface IHttpClient {

  Response submit(Request request) throws HttpConnectionException;

  WebSocket submit(Request request, WebSocketListener listener) throws HttpConnectionException;

  InetAddress getBoundLocalAddress();
}
