package io.contek.invoker.commons.actor.http;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.time.Duration;

@Immutable
public interface IHttpContext {

  String getBaseUrl();

  default boolean getLogHeaders() {
    return false;
  }

  default boolean getLogPayload() {
    return false;
  }

  default boolean getLogTimestamps() {
    return false;
  }

  @Nullable
  default Duration getConnectionTimeout() {
    return null;
  }

  @Nullable
  default Duration getReadTimeout() {
    return null;
  }

  @Nullable
  default Duration getWriteTimeout() {
    return null;
  }

  @Nullable
  default Duration getPingInterval() {
    return null;
  }
}
