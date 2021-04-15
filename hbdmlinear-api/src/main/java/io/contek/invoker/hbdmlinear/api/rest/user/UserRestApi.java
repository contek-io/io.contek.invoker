package io.contek.invoker.hbdmlinear.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class UserRestApi {

  private final IActor actor;
  private final RestContext context;

  public UserRestApi(IActor actor, RestContext context) {
    this.actor = actor;
    this.context = context;
  }
}
