package io.contek.invoker.binancedelivery.api.rest.user;

import io.contek.invoker.binancedelivery.api.rest.RestRequest;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;

import java.time.Clock;

import static com.google.common.base.Preconditions.checkArgument;

abstract class UserRestRequest<T> extends RestRequest<T> {

  private final Clock clock;

  UserRestRequest(IActor actor, RestContext context) {
    super(actor, context);
    clock = actor.clock();
    checkArgument(!actor.credential().isAnonymous());
  }

  long getMillis() {
    return clock.millis();
  }
}
