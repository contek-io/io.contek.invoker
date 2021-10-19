package io.contek.invoker.commons.actor.ratelimit;

import javax.annotation.concurrent.Immutable;

@Immutable
public enum LimitType {
  IP,
  API_KEY
}
