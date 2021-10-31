package io.contek.invoker.commons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class GsonFactory {
  private GsonFactory() {
  }

  public static Gson buildGson() {
    return new GsonBuilder()
        .registerTypeAdapter(Integer.class, new IntegerTypeAdapter())
        .registerTypeAdapter(int.class, new IntegerTypeAdapter())
        .registerTypeAdapter(Long.class, new LongTypeAdapter())
        .registerTypeAdapter(long.class, new LongTypeAdapter())
        .registerTypeAdapter(Double.class, new DoubleTypeAdapter())
        .registerTypeAdapter(double.class, new DoubleTypeAdapter())
        .create();
  }

  private static class IntegerTypeAdapter extends TypeAdapter<Integer> {
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

  private static class LongTypeAdapter extends TypeAdapter<Long> {
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

  private static class DoubleTypeAdapter extends TypeAdapter<Double> {
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
