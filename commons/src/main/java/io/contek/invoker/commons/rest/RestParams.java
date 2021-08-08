package io.contek.invoker.commons.rest;

import com.google.common.collect.ImmutableMap;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

@Immutable
public final class RestParams {

  private static final RestParams EMPTY = RestParams.newBuilder().build();

  private final Map<String, Object> values;

  private RestParams(Map<String, Object> values) {
    this.values = Collections.unmodifiableMap(new HashMap<>(values));
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static RestParams empty() {
    return EMPTY;
  }

  public static String toQueryString(Map<String, Object> params) {
    return params.entrySet().stream()
        .map(entry -> entry.getKey() + "=" + entry.getValue())
        .collect(joining("&"));
  }

  public boolean isEmpty() {
    return values.isEmpty();
  }

  public Map<String, Object> getValues() {
    return values;
  }

  public String getQueryString() {
    return toQueryString(values);
  }

  @NotThreadSafe
  public static final class Builder {

    private final Map<String, Object> values = new HashMap<>();

    private Builder() {}

    public Builder add(String key, long value) {
      values.put(key, value);
      return this;
    }

    public Builder add(String key, double value) {
      return add(key, BigDecimal.valueOf(value).toPlainString());
    }

    public Builder add(String key, boolean value) {
      values.put(key, value);
      return this;
    }

    public Builder add(String key, @Nullable Long value) {
      values.put(key, value);
      return this;
    }

    public Builder add(String key, @Nullable Double value) {
      if (value == null) {
        values.put(key, null);
        return this;
      }
      return add(key, BigDecimal.valueOf(value).toPlainString());
    }

    public Builder add(String key, @Nullable Boolean value) {
      values.put(key, value);
      return this;
    }

    public Builder add(String key, @Nullable String value) {
      values.put(key, value);
      return this;
    }

    public Builder addAll(Map<String, ?> values) {
      this.values.putAll(values);
      return this;
    }

    public RestParams build() {
      return new RestParams(values);
    }
  }
}
