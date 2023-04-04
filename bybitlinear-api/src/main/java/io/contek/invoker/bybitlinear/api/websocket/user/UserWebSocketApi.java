package io.contek.invoker.bybitlinear.api.websocket.user;

import io.contek.invoker.bybitlinear.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public final class UserWebSocketApi extends WebSocketApi {

  private final AtomicReference<OrderChannel> orderChannel = new AtomicReference<>();
  private final AtomicReference<PositionChannel> positionChannel = new AtomicReference<>();
  private final AtomicReference<ExecutionChannel> executionChannel = new AtomicReference<>();

  public UserWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public OrderChannel getOrderChannel() {
    synchronized (orderChannel) {
      OrderChannel channel = orderChannel.get();
      if (channel == null) {
        channel = new OrderChannel();
        attach(channel);
        orderChannel.set(channel);
      }
      return channel;
    }
  }

  public PositionChannel getPositionChannel() {
    synchronized (positionChannel) {
      PositionChannel channel = positionChannel.get();
      if (channel == null) {
        channel = new PositionChannel();
        attach(channel);
        positionChannel.set(channel);
      }
      return channel;
    }
  }

  public ExecutionChannel getExecutionChannel() {
    synchronized (executionChannel) {
      ExecutionChannel channel = executionChannel.get();
      if (channel == null) {
        channel = new ExecutionChannel();
        attach(channel);
        executionChannel.set(channel);
      }
      return channel;
    }
  }

  @Override
  protected String getUrlSuffix() {
    return "/realtime_private";
  }
}
