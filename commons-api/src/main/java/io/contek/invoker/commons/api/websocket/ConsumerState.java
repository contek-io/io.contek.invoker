package io.contek.invoker.commons.api.websocket;

import javax.annotation.concurrent.Immutable;

@Immutable
public enum ConsumerState {
  ACTIVE,
  IDLE,
  TERMINATED
}
