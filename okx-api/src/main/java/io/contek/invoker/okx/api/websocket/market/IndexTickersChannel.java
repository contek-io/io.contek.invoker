package io.contek.invoker.okx.api.websocket.market;

import io.contek.invoker.okx.api.common._IndexTickers;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketChannelKeys._index_tickers;

@ThreadSafe
public final class IndexTickersChannel extends WebSocketMarketChannel<IndexTickersChannel.Message> {

  IndexTickersChannel(IndexTickersChannel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketMarketChannelId<Message> {

    private Id(String instId) {
      super(_index_tickers, instId);
    }

    public static Id of(String instId) {
      return new Id(instId);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelPushData<_IndexTickers> {}
}
