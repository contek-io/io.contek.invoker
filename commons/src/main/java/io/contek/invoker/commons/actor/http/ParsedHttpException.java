package io.contek.invoker.commons.actor.http;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static java.lang.String.format;

@NotThreadSafe
public final class ParsedHttpException extends AnyHttpException {

  private final Object parsedEntity;

  public ParsedHttpException(int code, Object parsedEntity, String message) {
    super(code, message);
    this.parsedEntity = parsedEntity;
  }

  public Object getParsedEntity() {
    return parsedEntity;
  }

  public <T> T getParsedEntityAs(Class<T> type) {
    T result = tryGetParsedEntityAs(type);
    if (result == null) {
      throw new ClassCastException(
          format("Expected error type: %s, actual type: %s", type, parsedEntity.getClass()));
    }
    return result;
  }

  @Nullable
  public <T> T tryGetParsedEntityAs(Class<T> type) {
    if (type.isAssignableFrom(parsedEntity.getClass())) {
      return type.cast(parsedEntity);
    }
    return null;
  }
}
