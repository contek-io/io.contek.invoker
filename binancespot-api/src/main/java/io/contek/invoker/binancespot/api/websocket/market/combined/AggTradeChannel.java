package io.contek.invoker.binancespot.api.websocket.market.combined;

import io.contek.invoker.binancespot.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancespot.api.websocket.market.AggTradeEvent;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancespot.api.websocket.common.constants.WebSocketChannelKeys.aggTrade;

@ThreadSafe
public final class AggTradeChannel
    extends MarketCombinedChannel<AggTradeChannel.Message, AggTradeEvent> {

  AggTradeChannel(AggTradeChannel.Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends MarketCombinedChannelId<Message> {

    private Id(String symbol) {
      super(aggTrade(symbol));
    }

    public static Id of(String symbol) {
      return new Id(symbol);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketStreamMessage<AggTradeEvent> {}
}
