package io.contek.invoker.okx.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketAuthenticator;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.okx.api.rest.RestRequest;
import io.contek.invoker.okx.api.websocket.common.WebSocketLoginRequest;
import io.contek.invoker.okx.api.websocket.common.constants.WebSocketOutboundKeys;
import io.contek.invoker.security.ICredential;
import org.slf4j.Logger;

import java.time.Clock;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.slf4j.LoggerFactory.getLogger;

public final class WebSocketAuthenticator implements IWebSocketAuthenticator {

  private static final Logger log = getLogger(WebSocketAuthenticator.class);

  private final ICredential credential;
  private final Clock clock;

  private final AtomicBoolean authenticated = new AtomicBoolean();

  public WebSocketAuthenticator(ICredential credential, Clock clock) {
    this.credential = credential;
    this.clock = clock;
  }

  @Override
  public void handshake(WebSocketSession session) {
    if (credential.isAnonymous()) {
      return;
    }

    WebSocketLoginRequest request = new WebSocketLoginRequest();
    String currentSeconds = Long.toString(clock.instant().getEpochSecond());
    request.op = WebSocketOutboundKeys._login;
    WebSocketLoginRequest.Arg arg = new WebSocketLoginRequest.Arg();
    arg.apiKey = credential.getApiKeyId();
    arg.passphrase = credential.getProperties().get(RestRequest.OK_ACCESS_PASSPHRASE);
    arg.timestamp = currentSeconds;
    arg.sign = credential.sign(arg.timestamp + "GET/users/self/verify");
    request.args = ImmutableList.of(arg);

    log.info("Requesting authentication for {}.", credential.getApiKeyId());
    session.send(request);
    authenticated.set(true);
  }

  @Override
  public boolean isPending() {
    return false;
  }

  @Override
  public boolean isCompleted() {
    return credential.isAnonymous() || authenticated.get();
  }

  @Override
  public void onMessage(AnyWebSocketMessage message, WebSocketSession session) {}

  @Override
  public void afterDisconnect() {
    authenticated.set(false);
  }
}
