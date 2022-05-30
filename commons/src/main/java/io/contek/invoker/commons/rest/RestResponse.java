package io.contek.invoker.commons.rest;

import io.contek.invoker.commons.json.Json;
import io.vertx.core.json.DecodeException;

public final class RestResponse {

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
      return getStringValue() == null ? null : Json.decodeValue(getStringValue(), type);
    } catch (DecodeException e) {
      throw new RestParsingException(this, type, e);
    }
  }
}
