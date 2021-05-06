package io.contek.invoker.hbdminverse.api.rest.user;

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

  public PostSwapAccountInfo postSwapCrossAccountInfo() {
    return new PostSwapAccountInfo(actor, context);
  }

  public PostSwapAccountPositionInfo postSwapCrossAccountPositionInfo() {
    return new PostSwapAccountPositionInfo(actor, context);
  }

  public PostSwapAvailableLevelRate postSwapCrossAvailableLevelRate() {
    return new PostSwapAvailableLevelRate(actor, context);
  }

  public PostSwapCancel postSwapCrossCancel() {
    return new PostSwapCancel(actor, context);
  }

  public PostSwapHisorders postSwapCrossHisorders() {
    return new PostSwapHisorders(actor, context);
  }

  public PostSwapOpenorders postSwapCrossOpenorders() {
    return new PostSwapOpenorders(actor, context);
  }

  public PostSwapOrder postSwapCrossOrder() {
    return new PostSwapOrder(actor, context);
  }

  public PostSwapOrderInfo postSwapCrossOrderInfo() {
    return new PostSwapOrderInfo(actor, context);
  }

  public PostSwapPositionInfo postSwapCrossPositionInfo() {
    return new PostSwapPositionInfo(actor, context);
  }

  public PostSwapSwitchLeverRate postSwapCrossSwitchLeverRate() {
    return new PostSwapSwitchLeverRate(actor, context);
  }
}
