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

  public PostSwapAccountInfo postswapAccountInfo() {
    return new PostSwapAccountInfo(actor, context);
  }

  public PostSwapAccountPositionInfo postswapAccountPositionInfo() {
    return new PostSwapAccountPositionInfo(actor, context);
  }

  public PostSwapAvailableLevelRate postswapAvailableLevelRate() {
    return new PostSwapAvailableLevelRate(actor, context);
  }

  public PostSwapCancel postswapCancel() {
    return new PostSwapCancel(actor, context);
  }

  public PostSwapHisorders postswapHisorders() {
    return new PostSwapHisorders(actor, context);
  }

  public PostSwapOpenorders postswapOpenorders() {
    return new PostSwapOpenorders(actor, context);
  }

  public PostSwapOrder postswapOrder() {
    return new PostSwapOrder(actor, context);
  }

  public PostSwapOrderInfo postswapOrderInfo() {
    return new PostSwapOrderInfo(actor, context);
  }

  public PostSwapPositionInfo postswapPositionInfo() {
    return new PostSwapPositionInfo(actor, context);
  }

  public PostSwapSwitchLeverRate postswapSwitchLeverRate() {
    return new PostSwapSwitchLeverRate(actor, context);
  }
}
