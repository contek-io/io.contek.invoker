package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.deribit.api.websocket.WebSocketChannel;
import io.contek.invoker.deribit.api.websocket.WebSocketChannelId;
import io.contek.invoker.deribit.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSingleChannelMessage;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class MarketWebSocketChannel<
        Message extends WebSocketSingleChannelMessage<Data>, Data>
    extends WebSocketChannel<Message, Data> {

  MarketWebSocketChannel(
      WebSocketChannelId<Message> id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, "public", requestIdGenerator);
  }
}
