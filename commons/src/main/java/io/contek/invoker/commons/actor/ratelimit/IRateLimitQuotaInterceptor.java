package io.contek.invoker.commons.actor.ratelimit;

import javax.annotation.concurrent.ThreadSafe;
import java.util.List;
import java.util.function.BiFunction;

@ThreadSafe
public interface IRateLimitQuotaInterceptor
    extends BiFunction<String, List<TypedPermitRequest>, List<TypedPermitRequest>> {}
