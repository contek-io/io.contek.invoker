package io.contek.invoker.bitmex.api.rest.user;

import io.contek.invoker.bitmex.api.rest.RestRequest;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;

import static com.google.common.base.Preconditions.checkArgument;

abstract class UserRestRequest<T> extends RestRequest<T> {

  UserRestRequest(IActor actor, RestContext context) {
    super(actor, context);
    checkArgument(!actor.credential().isAnonymous());
  }
}
