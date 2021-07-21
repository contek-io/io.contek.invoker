package io.contek.invoker.ftx.api.websocket.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.ftx.api.websocket.WebSocketApi;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public final class UserWebSocketApi extends WebSocketApi {

    private final AtomicReference<OrderUpdateChannel> orderUpdateChannel = new AtomicReference<>();
    private final AtomicReference<FillUpdateChannel> fillUpdateChannel = new AtomicReference<>();

    public UserWebSocketApi(IActor actor, WebSocketContext context) {
        super(actor, context);
    }

    public OrderUpdateChannel getOrderUpdateChannel() {
        synchronized (orderUpdateChannel) {
            if (orderUpdateChannel.get() == null) {
                this.orderUpdateChannel.set(new OrderUpdateChannel());
                attach(this.orderUpdateChannel.get());
            }
            return orderUpdateChannel.get();
        }
    }

    public FillUpdateChannel getFillUpdateChannel() {
        synchronized (fillUpdateChannel) {
            if (fillUpdateChannel.get() == null) {
                this.fillUpdateChannel.set(new FillUpdateChannel());
                attach(this.fillUpdateChannel.get());
            }
            return fillUpdateChannel.get();
        }
    }
}