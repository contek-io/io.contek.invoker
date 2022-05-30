package io.contek.invoker.commons.actor.ratelimit;

public record TypedPermitRequest(String name, LimitType type, int permits) {

}
