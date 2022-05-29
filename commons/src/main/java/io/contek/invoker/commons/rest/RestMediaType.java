package io.contek.invoker.commons.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.MediaType;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public enum RestMediaType {
  JSON(
      requireNonNull(MediaType.parse("application/json; charset=utf-8")),
      RestMediaType::toJsonString),
  FORM(
      requireNonNull(MediaType.parse("application/x-www-form-urlencoded")),
      RestMediaType::toFormString);

  private static final Gson gson;

  static {
    GsonBuilder builder = new GsonBuilder();
    builder.serializeNulls();
    gson = builder.create();
  }

  private final MediaType value;
  private final Function<RestParams, String> composer;

  RestMediaType(MediaType value, Function<RestParams, String> composer) {
    this.value = value;
    this.composer = composer;
  }

  private static String toJsonString(RestParams params) {
    return gson.toJson(params.getValues());
  }

  private static String toFormString(RestParams params) {
    return params.getQueryString();
  }

  public String getValue() {
    return value.toString();
  }

  public RestMediaBody createBody(RestParams params) {
    return new RestMediaBody(value, composer.apply(params));
  }
}
