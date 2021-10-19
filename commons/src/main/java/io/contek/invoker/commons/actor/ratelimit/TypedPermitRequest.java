package io.contek.invoker.commons.actor.ratelimit;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class TypedPermitRequest {

  private final String name;
  private final LimitType type;
  private final int permits;

  public TypedPermitRequest(String name, LimitType type, int permits) {
    this.name = name;
    this.type = type;
    this.permits = permits;
  }

  public String getName() {
    return name;
  }

  public LimitType getType() {
    return type;
  }

  public int getPermits() {
    return permits;
  }
}
