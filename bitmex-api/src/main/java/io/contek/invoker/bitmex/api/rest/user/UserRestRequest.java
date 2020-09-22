package io.contek.invoker.bitmex.api.rest.user;

import static com.google.common.base.Preconditions.checkArgument;

import io.contek.invoker.bitmex.api.rest.RestRequest;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.rest.RestContext;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
abstract class UserRestRequest<T> extends RestRequest<T> {

  UserRestRequest(IActor actor, RestContext context) {
    super(actor, context);
    checkArgument(!actor.getCredential().isAnonymous());
  }
}
