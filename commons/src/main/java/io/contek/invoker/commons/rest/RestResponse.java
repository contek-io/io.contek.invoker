package io.contek.invoker.commons.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.io.IOException;

@Immutable
public final class RestResponse {

  private static final Gson gson = new GsonBuilder()
      .registerTypeAdapter(Integer.class, new EmptyStringToIntegerTypeAdapter())
      .registerTypeAdapter(int.class, new EmptyStringToIntegerTypeAdapter())
      .registerTypeAdapter(Long.class, new EmptyStringToLongTypeAdapter())
      .registerTypeAdapter(long.class, new EmptyStringToLongTypeAdapter())
      .registerTypeAdapter(Double.class, new EmptyStringToDoubleTypeAdapter())
      .registerTypeAdapter(double.class, new EmptyStringToDoubleTypeAdapter())
      .create();

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
      throw new RestParsingException(this, type, e);
    }
  }

  private static class EmptyStringToIntegerTypeAdapter extends TypeAdapter<Integer> {
    @Override
    public void write(JsonWriter out, Integer value) throws IOException {
      if (value == null) {
        out.nullValue();
        return;
      }
      out.value(value);
    }

    @Override
    public Integer read(JsonReader in) throws IOException {
      if (in.peek() == JsonToken.NULL) {
        in.nextNull();
        return null;
      }

      try {
        String value = in.nextString();
        if ("".equals(value)) {
          return null;
        }
        return Integer.valueOf(value);
      } catch (NumberFormatException e) {
        throw new JsonSyntaxException(e);
      }
    }
  }

  private static class EmptyStringToLongTypeAdapter extends TypeAdapter<Long> {
    @Override
    public void write(JsonWriter out, Long value) throws IOException {
      if (value == null) {
        out.nullValue();
        return;
      }
      out.value(value);
    }

    @Override
    public Long read(JsonReader in) throws IOException {
      if (in.peek() == JsonToken.NULL) {
        in.nextNull();
        return null;
      }

      try {
        String value = in.nextString();
        if ("".equals(value)) {
          return null;
        }
        return Long.valueOf(value);
      } catch (NumberFormatException e) {
        throw new JsonSyntaxException(e);
      }
    }
  }

  private static class EmptyStringToDoubleTypeAdapter extends TypeAdapter<Double> {
    @Override
    public void write(JsonWriter out, Double value) throws IOException {
      if (value == null) {
        out.nullValue();
        return;
      }
      out.value(value);
    }

    @Override
    public Double read(JsonReader in) throws IOException {
      if (in.peek() == JsonToken.NULL) {
        in.nextNull();
        return null;
      }

      try {
        String value = in.nextString();
        if ("".equals(value)) {
          return null;
        }
        return Double.valueOf(value);
      } catch (NumberFormatException e) {
        throw new JsonSyntaxException(e);
      }
    }
  }
}
