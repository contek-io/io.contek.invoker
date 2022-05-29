package io.contek.invoker.hbdminverse.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;

public final class UserRestApi {

  private final IActor actor;
  private final RestContext context;

  public UserRestApi(IActor actor, RestContext context) {
    this.actor = actor;
    this.context = context;
  }

  public PostSwapAccountInfo postSwapAccountInfo() {
    return new PostSwapAccountInfo(actor, context);
  }

  public PostSwapAccountPositionInfo postSwapAccountPositionInfo() {
    return new PostSwapAccountPositionInfo(actor, context);
  }

  public PostSwapAvailableLevelRate postSwapAvailableLevelRate() {
    return new PostSwapAvailableLevelRate(actor, context);
  }

  public PostSwapCancel postSwapCancel() {
    return new PostSwapCancel(actor, context);
  }

  public PostSwapHisorders postSwapHisorders() {
    return new PostSwapHisorders(actor, context);
  }

  public PostSwapOpenorders postSwapOpenorders() {
    return new PostSwapOpenorders(actor, context);
  }

  public PostSwapOrder postSwapOrder() {
    return new PostSwapOrder(actor, context);
  }

  public PostSwapOrderInfo postSwapOrderInfo() {
    return new PostSwapOrderInfo(actor, context);
  }

  public PostSwapPositionInfo postSwapPositionInfo() {
    return new PostSwapPositionInfo(actor, context);
  }

  public PostSwapSwitchLeverRate postSwapSwitchLeverRate() {
    return new PostSwapSwitchLeverRate(actor, context);
  }
}
