package io.contek.invoker.ftx.api.websocket.user;

import io.contek.invoker.ftx.api.websocket.WebSocketChannel;
import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import java.util.Objects;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._fills;

@ThreadSafe
public final class FillUpdateChannel
        extends WebSocketChannel<FillUpdateChannel.Message> {

    FillUpdateChannel() {
    }

    @Override
    protected String getDisplayName() {
        return _fills;
    }

    @Override
    protected Class<Message> getMessageType() {
        return FillUpdateChannel.Message.class;
    }

    @Override
    protected boolean accepts(Message message) {
        return true;
    }

    @Override
    protected String getChannel() {
        return _fills;
    }

    @Override
    protected String getMarket() {
        return null;
    }

    @NotThreadSafe
    public static final class Data {
        public double fee;
        public double feeRate;
        public String future;
        public long id;
        public String liquidity;
        public String market;
        public long orderId;
        public long tradeId;
        public double price;
        public String side;
        public double size;
        public String time;
        public String type;

        @Override
        public String toString() {
            return "Data{" +
                    "fee=" + fee +
                    ", feeRate=" + feeRate +
                    ", future='" + future + '\'' +
                    ", id=" + id +
                    ", liquidity='" + liquidity + '\'' +
                    ", market='" + market + '\'' +
                    ", orderId=" + orderId +
                    ", tradeId=" + tradeId +
                    ", price=" + price +
                    ", side='" + side + '\'' +
                    ", size=" + size +
                    ", time='" + time + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    @NotThreadSafe
    public static final class Message extends WebSocketChannelMessage<Data> {

    }
}
