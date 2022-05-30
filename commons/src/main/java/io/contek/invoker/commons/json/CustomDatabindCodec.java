package io.contek.invoker.commons.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.netty.buffer.ByteBufInputStream;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.EncodeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.jackson.DatabindCodec;
import io.vertx.core.json.jackson.VertxModule;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class CustomDatabindCodec extends DatabindCodec {

  private static final ObjectMapper mapper = new ObjectMapper();
  private static final ObjectMapper prettyMapper = new ObjectMapper();

  static {
    initialize();
  }

  private static void initialize() {
    // Non-standard JSON but we allow C style comments in our JSON
    mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    prettyMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    prettyMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    prettyMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    VertxModule module = new VertxModule();
    mapper.registerModule(module);
    prettyMapper.registerModule(module);
  }

  /**
   * @return the {@link ObjectMapper} used for data binding.
   */
  public static ObjectMapper mapper() {
    return mapper;
  }

  /**
   * @return the {@link ObjectMapper} used for data binding configured for indenting output.
   */
  public static ObjectMapper prettyMapper() {
    return prettyMapper;
  }

  public static JsonParser createParser(Buffer buf) {
    try {
      return CustomDatabindCodec.mapper
          .getFactory()
          .createParser((InputStream) new ByteBufInputStream(buf.getByteBuf()));
    } catch (IOException e) {
      throw new DecodeException("Failed to decode:" + e.getMessage(), e);
    }
  }

  public static JsonParser createParser(String str) {
    try {
      return CustomDatabindCodec.mapper.getFactory().createParser(str);
    } catch (IOException e) {
      throw new DecodeException("Failed to decode:" + e.getMessage(), e);
    }
  }

  public static <T> T fromParser(JsonParser parser, Class<T> type) throws DecodeException {
    T value;
    JsonToken remaining;
    try {
      value = CustomDatabindCodec.mapper.readValue(parser, type);
      remaining = parser.nextToken();
    } catch (Exception e) {
      throw new DecodeException("Failed to decode:" + e.getMessage(), e);
    } finally {
      close(parser);
    }
    if (remaining != null) {
      throw new DecodeException("Unexpected trailing token");
    }
    if (type == Object.class) {
      value = (T) adapt(value);
    }
    return value;
  }

  static void close(Closeable parser) {
    try {
      parser.close();
    } catch (IOException ignore) {
    }
  }

  private static <T> T fromParser(JsonParser parser, TypeReference<T> type) throws DecodeException {
    T value;
    try {
      value = CustomDatabindCodec.mapper.readValue(parser, type);
    } catch (Exception e) {
      throw new DecodeException("Failed to decode:" + e.getMessage(), e);
    } finally {
      close(parser);
    }
    if (type.getType() == Object.class) {
      value = (T) adapt(value);
    }
    return value;
  }

  private static Object adapt(Object o) {
    try {
      if (o instanceof List) {
        List list = (List) o;
        return new JsonArray(list);
      } else if (o instanceof Map) {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) o;
        return new JsonObject(map);
      }
      return o;
    } catch (Exception e) {
      throw new DecodeException("Failed to decode: " + e.getMessage());
    }
  }

  @Override
  public <T> T fromValue(Object json, Class<T> clazz) {
    T value = CustomDatabindCodec.mapper.convertValue(json, clazz);
    if (clazz == Object.class) {
      value = (T) adapt(value);
    }
    return value;
  }

  public <T> T fromValue(Object json, TypeReference<T> type) {
    T value = CustomDatabindCodec.mapper.convertValue(json, type);
    if (type.getType() == Object.class) {
      value = (T) adapt(value);
    }
    return value;
  }

  @Override
  public <T> T fromString(String str, Class<T> clazz) throws DecodeException {
    return fromParser(createParser(str), clazz);
  }

  public <T> T fromString(String str, TypeReference<T> typeRef) throws DecodeException {
    return fromParser(createParser(str), typeRef);
  }

  @Override
  public <T> T fromBuffer(Buffer buf, Class<T> clazz) throws DecodeException {
    return fromParser(createParser(buf), clazz);
  }

  public <T> T fromBuffer(Buffer buf, TypeReference<T> typeRef) throws DecodeException {
    return fromParser(createParser(buf), typeRef);
  }

  @Override
  public String toString(Object object, boolean pretty) throws EncodeException {
    try {
      ObjectMapper mapper = pretty ? CustomDatabindCodec.prettyMapper : CustomDatabindCodec.mapper;
      return mapper.writeValueAsString(object);
    } catch (Exception e) {
      throw new EncodeException("Failed to encode as JSON: " + e.getMessage());
    }
  }

  @Override
  public Buffer toBuffer(Object object, boolean pretty) throws EncodeException {
    try {
      ObjectMapper mapper = pretty ? CustomDatabindCodec.prettyMapper : CustomDatabindCodec.mapper;
      return Buffer.buffer(mapper.writeValueAsBytes(object));
    } catch (Exception e) {
      throw new EncodeException("Failed to encode as JSON: " + e.getMessage());
    }
  }
}
