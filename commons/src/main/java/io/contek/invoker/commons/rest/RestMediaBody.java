package io.contek.invoker.commons.rest;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public final class RestMediaBody {

  private final MediaType type;
  private final String stringValue;

  public RestMediaBody(MediaType type, String stringValue) {
    this.type = type;
    this.stringValue = stringValue;
  }

  public String getStringValue() {
    return stringValue;
  }

  RequestBody createRequestBody() {
    return RequestBody.create(type, stringValue);
  }
}
