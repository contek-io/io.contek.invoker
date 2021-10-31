package io.contek.invoker.commons.rest;

import com.google.gson.Gson;
import io.contek.invoker.commons.GsonFactory;
import okhttp3.MediaType;

import javax.annotation.concurrent.Immutable;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

@Immutable
public enum RestMediaType {
  JSON(
      requireNonNull(MediaType.parse("application/json; charset=utf-8")),
      RestMediaType::toJsonString),
  FORM(
      requireNonNull(MediaType.parse("application/x-www-form-urlencoded")),
      RestMediaType::toFormString);

  private static final Gson gson = GsonFactory.buildGson();

  private final MediaType value;
  private final Function<RestParams, String> composer;

  RestMediaType(MediaType value, Function<RestParams, String> composer) {
    this.value = value;
    this.composer = composer;
  }

  public String getValue() {
    return value.toString();
  }

  public RestMediaBody createBody(RestParams params) {
    return new RestMediaBody(value, composer.apply(params));
  }

  private static String toJsonString(RestParams params) {
    return gson.toJson(params.getValues());
  }

  private static String toFormString(RestParams params) {
    return params.getQueryString();
  }
}
