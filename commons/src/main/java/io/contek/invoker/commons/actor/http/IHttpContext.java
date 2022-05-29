package io.contek.invoker.commons.actor.http;

import java.time.Duration;

public interface IHttpContext {

  String getBaseUrl();

  default Duration getConnectionTimeout() {
    return null;
  }

  default Duration getReadTimeout() {
    return null;
  }

  default Duration getWriteTimeout() {
    return null;
  }

  default Duration getPingInterval() {
    return null;
  }
}
