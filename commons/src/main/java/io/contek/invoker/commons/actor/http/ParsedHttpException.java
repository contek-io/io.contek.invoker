package io.contek.invoker.commons.actor.http;

import static java.lang.String.format;

public final class ParsedHttpException extends AnyHttpException {

  private final Object parsedEntity;

  public ParsedHttpException(Object parsedEntity, String message) {
    super(message);
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

  public <T> T tryGetParsedEntityAs(Class<T> type) {
    if (type.isAssignableFrom(parsedEntity.getClass())) {
      return type.cast(parsedEntity);
    }
    return null;
  }
}
