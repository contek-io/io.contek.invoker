package io.contek.invoker.commons;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UriEncoder {

  public static String encode(String s) {
    String result;
    try {
      result =
          URLEncoder.encode(s, StandardCharsets.UTF_8)
              .replaceAll("\\+", "%20")
              .replaceAll("\\%21", "!")
              .replaceAll("\\%27", "'")
              .replaceAll("\\%28", "(")
              .replaceAll("\\%29", ")")
              .replaceAll("\\%7E", "~");
    } // This exception should never occur.
    catch (Exception e) {
      result = s;
    }

    return result;
  }
}
