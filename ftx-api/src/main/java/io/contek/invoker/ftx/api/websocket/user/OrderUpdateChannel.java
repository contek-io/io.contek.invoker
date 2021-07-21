package io.contek.invoker.ftx.api.websocket.user;

import io.contek.invoker.ftx.api.common._Order;
import io.contek.invoker.ftx.api.websocket.WebSocketChannel;
import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._orders;

@ThreadSafe
public final class OrderUpdateChannel
        extends WebSocketChannel<OrderUpdateChannel.Message> {

    OrderUpdateChannel() {
    }

    @Override
    protected String getDisplayName() {
        return _orders;
    }

    @Override
    public Class<Message> getMessageType() {
        return Message.class;
    }

    @Override
    protected boolean accepts(Message message) {
        return true;
    }

    @Override
    protected String getChannel() {
        return _orders;
    }

    @Override
    protected String getMarket() {
        return null;
    }

    @NotThreadSafe
    public static final class Data extends _Order {

        @Override
        public String toString() {
            return super.toString();
        }
    }

    @NotThreadSafe
    public static final class Message extends WebSocketChannelMessage<Data> {

    }
}
