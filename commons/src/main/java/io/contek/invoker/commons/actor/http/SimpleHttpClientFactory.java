package io.contek.invoker.commons.actor.http;

import okhttp3.OkHttpClient;

import java.net.InetAddress;
import java.time.Duration;

import static java.net.InetAddress.getLoopbackAddress;

public final class SimpleHttpClientFactory implements IHttpClientFactory {

  private static final InetAddress LOCAL_HOST = getLoopbackAddress();

  private SimpleHttpClientFactory() {}

  public static SimpleHttpClientFactory getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public IHttpClient create(IHttpContext context) {
    OkHttpClient.Builder builder =
        new OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor.getInstance());

    Duration connectionTimeout = context.getConnectionTimeout();
    if (connectionTimeout != null) {
      builder.connectTimeout(connectionTimeout);
    }

    Duration readTimeout = context.getReadTimeout();
    if (readTimeout != null) {
      builder.readTimeout(readTimeout);
    }

    Duration writeTimeout = context.getWriteTimeout();
    if (writeTimeout != null) {
      builder.writeTimeout(writeTimeout);
    }

    Duration pingInterval = context.getPingInterval();
    if (pingInterval != null) {
      builder.pingInterval(pingInterval);
    }

    return new SimpleHttpClient(builder.build(), LOCAL_HOST);
  }

  private static final class InstanceHolder {

    private static final SimpleHttpClientFactory INSTANCE = new SimpleHttpClientFactory();
  }
}
