package io.contek.invoker.bitmex.api.rest;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class RestError {

  private final Details error;

  RestError(Details error) {
    this.error = error;
  }

  public Details getError() {
    return error;
  }

  @Immutable
  public static final class Details {

    private final String message;
    private final String name;

    Details(String message, String name) {
      this.message = message;
      this.name = name;
    }

    public String getMessage() {
      return message;
    }

    public String getName() {
      return name;
    }
  }
}
