package io.contek.invoker.commons.rest;

import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.joining;

@Immutable
public final class RestParams {

  private static final RestParams EMPTY = RestParams.newBuilder().build();

  private final Map<String, Object> values;

  private RestParams(Map<String, Object> values) {
    this.values = Collections.unmodifiableMap(values);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static RestParams empty() {
    return EMPTY;
  }

  public Builder toBuilder() {
    return newBuilder().addAll(values);
  }

  public boolean isEmpty() {
    return values.isEmpty();
  }

  public Map<String, Object> getValues() {
    return values;
  }

  public String getQueryString() {
    return getQueryString(Escapers.nullEscaper());
  }

  public String getQueryString(Escaper escaper) {
    return toQueryString(values, escaper);
  }

  private static String toQueryString(Map<String, Object> params, Escaper escaper) {
    return params.entrySet().stream()
        .map(entry -> entry.getKey() + "=" + escaper.escape(entry.getValue().toString()))
        .collect(joining("&"));
  }

  @NotThreadSafe
  public static final class Builder {

    private final Map<String, Object> values = new LinkedHashMap<>();

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
      return build(false);
    }

    public RestParams build(boolean sort) {
      if (sort) {
        return new RestParams(new TreeMap<>(values));
      }
      return new RestParams(values);
    }
  }
}
