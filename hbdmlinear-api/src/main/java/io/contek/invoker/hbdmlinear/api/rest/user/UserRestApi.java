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

  public PostSwapCrossAccountInfo postSwapCrossAccountInfo() {
    return new PostSwapCrossAccountInfo(actor, context);
  }

  public PostSwapCrossAccountPositionInfo postSwapCrossAccountPositionInfo() {
    return new PostSwapCrossAccountPositionInfo(actor, context);
  }

  public PostSwapCrossAvailableLevelRate postSwapCrossAvailableLevelRate() {
    return new PostSwapCrossAvailableLevelRate(actor, context);
  }

  public PostSwapCrossCancel postSwapCrossCancel() {
    return new PostSwapCrossCancel(actor, context);
  }

  public PostSwapCrossOpenorders postSwapCrossOpenorders() {
    return new PostSwapCrossOpenorders(actor, context);
  }

  public PostSwapCrossOrder postSwapCrossOrder() {
    return new PostSwapCrossOrder(actor, context);
  }

  public PostSwapCrossOrderInfo postSwapCrossOrderInfo() {
    return new PostSwapCrossOrderInfo(actor, context);
  }

  public PostSwapCrossPositionInfo postSwapCrossPositionInfo() {
    return new PostSwapCrossPositionInfo(actor, context);
  }

  public PostSwapCrossSwitchLeverRate postSwapCrossSwitchLeverRate() {
    return new PostSwapCrossSwitchLeverRate(actor, context);
  }
}
