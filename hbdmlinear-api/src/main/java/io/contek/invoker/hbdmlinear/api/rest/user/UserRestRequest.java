package io.contek.invoker.hbdmlinear.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.hbdmlinear.api.rest.RestRequest;

import static com.google.common.base.Preconditions.checkArgument;
import static io.contek.invoker.commons.rest.RestMethod.POST;

abstract class UserRestRequest<T> extends RestRequest<T> {

  UserRestRequest(IActor actor, RestContext context) {
    super(actor, context);
    checkArgument(!actor.getCredential().isAnonymous());
  }

  @Override
  protected final RestMethod getMethod() {
    return POST;
  }
}
