package io.contek.invoker.hbdmlinear.api.websocket.common.notification;

import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;
import java.net.URI;
import java.time.Clock;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static com.google.common.net.UrlEscapers.urlFormParameterEscaper;
import static io.contek.invoker.hbdmlinear.api.websocket.user.constants.AuthTypeKeys._api;
import static io.contek.invoker.hbdmlinear.api.websocket.user.constants.OpKeys._auth;
import static java.time.ZoneOffset.UTC;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static java.time.temporal.ChronoField.MILLI_OF_SECOND;

@ThreadSafe
final class NotificationWebSocketAuthenticator implements IWebSocketAuthenticator {

  private static final DateTimeFormatter FORMATTER = ISO_LOCAL_DATE_TIME.withZone(UTC);

  private final ICredential credential;
  private final String path;
  private final NotificationWebSocketRequestIdGenerator requestIdGenerator;
  private final WebSocketContext context;
  private final Clock clock;

  private final AtomicBoolean authenticated = new AtomicBoolean();
  private final AtomicReference<NotificationWebSocketAuthRequest> pendingCommandHolder =
      new AtomicReference<>(null);

  NotificationWebSocketAuthenticator(
      ICredential credential,
      String path,
      NotificationWebSocketRequestIdGenerator requestIdGenerator,
      WebSocketContext context,
      Clock clock) {
    this.credential = credential;
    this.path = path;
    this.requestIdGenerator = requestIdGenerator;
    this.context = context;
    this.clock = clock;
  }

  @Override
  public void handshake(WebSocketSession session) {
    if (credential.isAnonymous()) {
      return;
    }

    synchronized (pendingCommandHolder) {
      NotificationWebSocketAuthRequest request = new NotificationWebSocketAuthRequest();
      request.op = _auth;
      request.type = _api;
      request.cid = requestIdGenerator.generateNext();
      request.AccessKeyId = credential.getApiKeyId();
      request.SignatureMethod = "HmacSHA256";
      request.SignatureVersion = "2";
      request.Timestamp = FORMATTER.format(clock.instant().with(MILLI_OF_SECOND, 0));
      request.Signature = generateSignature(request);
      session.send(request);
      pendingCommandHolder.set(request);
    }
  }

  @Override
  public boolean isPending() {
    return pendingCommandHolder.get() != null;
  }

  @Override
  public boolean isCompleted() {
    return authenticated.get() || credential.isAnonymous();
  }

  @Override
  public void onMessage(AnyWebSocketMessage message, WebSocketSession session) {
    if (isCompleted()) {
      return;
    }

    if (!(message instanceof NotificationWebSocketConfirmation response)) {
      return;
    }

    synchronized (pendingCommandHolder) {
      NotificationWebSocketAuthRequest request = pendingCommandHolder.get();
      if (request == null) {
        return;
      }

      if (!request.cid.equals(response.cid)) {
        return;
      }

      pendingCommandHolder.set(null);
      if (response.err_code != 0) {
        throw new WebSocketIllegalMessageException(response.err_code + ": " + response.err_msg);
      }

      reset();
      authenticated.set(true);
    }
  }

  @Override
  public void afterDisconnect() {
    reset();
  }

  private String generateSignature(NotificationWebSocketAuthRequest request) {
    RestParams.Builder builder = RestParams.newBuilder();
    builder.add("AccessKeyId", request.AccessKeyId);
    builder.add("SignatureMethod", request.SignatureMethod);
    builder.add("SignatureVersion", request.SignatureVersion);
    builder.add("Timestamp", request.Timestamp);
    RestParams withIdentity = builder.build(true);
    String queryString = withIdentity.getQueryString(urlFormParameterEscaper());
    String payload =
        String.join("\n", "GET", URI.create(context.getBaseUrl()).getHost(), path, queryString);
    return credential.sign(payload);
  }

  private void reset() {
    authenticated.set(false);
    synchronized (pendingCommandHolder) {
      pendingCommandHolder.set(null);
    }
  }
}
