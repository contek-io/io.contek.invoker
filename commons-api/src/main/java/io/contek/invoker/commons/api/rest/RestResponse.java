package io.contek.invoker.commons.api.rest;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class RestResponse {

  private static final Gson gson = new Gson();

  private final int code;
  private final String stringValue;

  RestResponse(int code, @Nullable String stringValue) {
    this.code = code;
    this.stringValue = stringValue;
  }

  public int getCode() {
    return code;
  }

  @Nullable
  public String getStringValue() {
    return stringValue;
  }

  @Nullable
  public <T> T getAs(Class<T> type) throws RestParsingException {
    try {
      return stringValue == null ? null : gson.fromJson(stringValue, type);
    } catch (JsonSyntaxException e) {
      throw new RestParsingException(this, type);
    }
  }
}
