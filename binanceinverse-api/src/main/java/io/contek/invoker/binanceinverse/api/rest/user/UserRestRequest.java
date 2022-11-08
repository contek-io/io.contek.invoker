package io.contek.invoker.binanceinverse.api.rest.user;

import io.contek.invoker.binanceinverse.api.rest.RestRequest;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;

import javax.annotation.concurrent.NotThreadSafe;
import java.time.Clock;

import static com.google.common.base.Preconditions.checkArgument;

@NotThreadSafe
abstract class UserRestRequest<T> extends RestRequest<T> {

  private final Clock clock;

  UserRestRequest(IActor actor, RestContext context) {
    super(actor, context);
    clock = actor.getClock();
    checkArgument(!actor.getCredential().isAnonymous());
  }

  long getMillis() {
    return clock.millis();
  }
}
