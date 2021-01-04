package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.Immutable;

@Immutable
public enum ConsumerState {
  ACTIVE,
  IDLE,
  TERMINATED
}
