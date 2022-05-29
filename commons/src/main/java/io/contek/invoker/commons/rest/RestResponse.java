package io.contek.invoker.commons.rest;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public final class RestResponse {

  private static final Gson gson = new Gson();

  private final int code;
  private final String stringValue;

  RestResponse(int code, String stringValue) {
    this.code = code;
    this.stringValue = stringValue;
  }

  public int getCode() {
    return code;
  }

  public String getStringValue() {
    return stringValue;
  }

  public <T> T getAs(Class<T> type) throws RestParsingException {
    try {
      return stringValue == null ? null : gson.fromJson(stringValue, type);
    } catch (JsonSyntaxException e) {
      throw new RestParsingException(this, type, e);
    }
  }
}
