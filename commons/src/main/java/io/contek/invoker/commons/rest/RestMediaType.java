package io.contek.invoker.commons.rest;

import com.google.common.net.MediaType;
import io.contek.invoker.commons.json.Json;
import io.vertx.core.buffer.Buffer;

public enum RestMediaType {
  JSON(MediaType.JSON_UTF_8),
  FORM(MediaType.FORM_DATA);

  private final MediaType value;

  RestMediaType(MediaType value) {
    this.value = value;
  }

  private static Buffer toJson(RestParams params) {
    return Json.encodeToBuffer(params.values());
  }

  private static Buffer toForm(RestParams params) {
    return Buffer.buffer(params.getQueryString());
  }

  public String getValue() {
    return value.toString();
  }

  public RestMediaBody create(RestParams params) {
    return switch (this) {
      case JSON -> new RestMediaBody(value, toJson(params));
      case FORM -> new RestMediaBody(value, toForm(params));
    };
  }
}
