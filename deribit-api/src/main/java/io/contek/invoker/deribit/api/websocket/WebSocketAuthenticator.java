package io.contek.invoker.deribit.api.websocket;

import com.google.common.io.BaseEncoding;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketAuthenticator;
import io.contek.invoker.commons.websocket.WebSocketAuthenticationException;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.deribit.api.common._Error;
import io.contek.invoker.deribit.api.websocket.common.WebSocketRequest;
import io.contek.invoker.deribit.api.websocket.common.WebSocketResponse;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Clock;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static com.google.common.io.BaseEncoding.base32Hex;
import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketAuthGrantTypeKeys._client_signature;

@ThreadSafe
final class WebSocketAuthenticator implements IWebSocketAuthenticator {

  private final ICredential credential;
  private final WebSocketRequestIdGenerator requestIdGenerator;
  private final Clock clock;

  private final BaseEncoding ENCODING = base32Hex().lowerCase().omitPadding();
  private final Random random = new Random();

  private final AtomicReference<WebSocketRequest<AuthParams>> pendingRequestHolder =
      new AtomicReference<>();
  private final AtomicBoolean authenticated = new AtomicBoolean();

  WebSocketAuthenticator(
      ICredential credential, WebSocketRequestIdGenerator requestIdGenerator, Clock clock) {
    this.credential = credential;
    this.requestIdGenerator = requestIdGenerator;
    this.clock = clock;
  }

  @Override
  public void handshake(WebSocketSession session) {
    if (credential.isAnonymous()) {
      return;
    }

    String clientId = credential.getApiKeyId();
    String timestamp = Long.toString(clock.instant().toEpochMilli());
    String nonce = getNonce();
    String data = "";

    String payload = timestamp + "\n" + nonce + "\n" + data;
    String signature = credential.sign(payload);

    AuthParams params = new AuthParams();
    params.grant_type = _client_signature;
    params.client_id = clientId;
    params.timestamp = timestamp;
    params.nonce = nonce;
    params.data = data;
    params.signature = signature;

    WebSocketRequest<AuthParams> request = new WebSocketRequest<>();
    request.id = requestIdGenerator.getNextRequestId();
    request.method = "public/auth";
    request.params = params;
    session.send(request);
    pendingRequestHolder.set(request);
  }

  @Override
  public boolean isCompleted() {
    return credential.isAnonymous() || authenticated.get();
  }

  @Override
  public void onMessage(AnyWebSocketMessage message, WebSocketSession session) {
    if (isCompleted()) {
      return;
    }

    if (!(message instanceof WebSocketResponse)) {
      return;
    }

    WebSocketRequest<AuthParams> request = pendingRequestHolder.get();
    if (request == null) {
      return;
    }

    WebSocketResponse response = (WebSocketResponse) message;
    if (!response.id.equals(request.id)) {
      return;
    }

    if (response.error != null) {
      _Error error = response.error;
      throw new WebSocketAuthenticationException(error.code + ": " + error.message);
    }
    authenticated.set(true);
  }

  @Override
  public void afterDisconnect() {
    pendingRequestHolder.set(null);
    authenticated.set(false);
  }

  private String getNonce() {
    byte[] bytes = new byte[8];
    while (true) {
      random.nextBytes(bytes);
      String encoded = ENCODING.encode(bytes);
      if (encoded.length() >= 8) {
        return encoded.substring(0, 8);
      }
    }
  }

  @NotThreadSafe
  public static final class AuthParams {

    public String grant_type;
    public String client_id;
    public String timestamp;
    public String nonce;
    public String data;
    public String signature;
  }
}
