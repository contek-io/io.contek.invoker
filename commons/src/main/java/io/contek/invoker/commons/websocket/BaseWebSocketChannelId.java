package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.Immutable;
import java.util.Objects;

@Immutable
public abstract class BaseWebSocketChannelId {

  private final String value;

  protected BaseWebSocketChannelId(String value) {
    this.value = value;
  }

  public final String getValue() {
    return value;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BaseWebSocketChannelId that = (BaseWebSocketChannelId) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public final int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public final String toString() {
    return getValue();
  }
}
