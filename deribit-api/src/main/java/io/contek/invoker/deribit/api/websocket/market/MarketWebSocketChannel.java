package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.deribit.api.websocket.WebSocketChannel;
import io.contek.invoker.deribit.api.websocket.WebSocketChannelId;
import io.contek.invoker.deribit.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSingleChannelMessage;

public abstract class MarketWebSocketChannel<
        Id extends WebSocketChannelId<Message>, Message extends WebSocketSingleChannelMessage<?>>
    extends WebSocketChannel<Id, Message> {

  MarketWebSocketChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, "public", requestIdGenerator);
  }
}
