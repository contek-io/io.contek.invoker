package io.contek.invoker.commons.actor.ratelimit;

import java.util.List;
import java.util.function.BiFunction;

public interface IRateLimitQuotaInterceptor
    extends BiFunction<String, List<TypedPermitRequest>, List<TypedPermitRequest>> {}
