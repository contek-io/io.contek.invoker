package io.contek.invoker.commons.rest;

import static java.util.stream.Collectors.joining;

import com.google.common.collect.ImmutableSortedMap;
import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

@Immutable
public final class RestParams {

  private static final RestParams EMPTY = RestParams.newBuilder().build();

  private final Map<String, Object> values;

  private RestParams(Map<String, Object> values) {
    this.values = values;
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

    public Builder add(String key, Double value) {
      return value == null ? add(key) : add(key, BigDecimal.valueOf(value).toPlainString());
    }

    public Builder add(String key, boolean value) {
      values.put(key, value);
      return this;
    }

    public Builder add(String key) {
      values.put(key, null);
      return this;
    }

    public Builder add(String key, String value) {
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
        return new RestParams(ImmutableSortedMap.copyOf(values));
      }
      return new RestParams(Collections.unmodifiableMap(values));
    }
  }
}
