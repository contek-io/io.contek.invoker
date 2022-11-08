package io.contek.invoker.binancelinear.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketMethods {

  public static final String SUBSCRIBE = "SUBSCRIBE";

  public static final String UNSUBSCRIBE = "UNSUBSCRIBE";

  public static final String LIST_SUBSCRIPTIONS = "LIST_SUBSCRIPTIONS";

  public static final String SET_PROPERTY = "SET_PROPERTY";

  public static final String GET_PROPERTY = "GET_PROPERTY";

  private WebSocketMethods() {}
}
