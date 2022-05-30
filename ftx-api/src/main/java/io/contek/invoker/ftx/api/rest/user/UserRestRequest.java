package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.ftx.api.rest.RestRequest;

import static com.google.common.base.Preconditions.checkArgument;

abstract class UserRestRequest<T> extends RestRequest<T> {

  UserRestRequest(IActor actor, RestContext context) {
    super(actor, context);
    checkArgument(!actor.credential().isAnonymous());
  }
}
