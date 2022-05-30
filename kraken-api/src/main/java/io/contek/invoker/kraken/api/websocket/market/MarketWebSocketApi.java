package io.contek.invoker.kraken.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.kraken.api.websocket.WebSocketApi;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<BookChannel.Id, BookChannel> bookChannels = new ConcurrentHashMap<>();
  private final Map<TradeChannel.Id, TradeChannel> tradeChannels = new ConcurrentHashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public BookChannel getBookChannel(BookChannel.Id id) {
    return bookChannels.computeIfAbsent(
        id,
        k -> {
          BookChannel result = new BookChannel(k, getRequestIdGenerator());
          attach(result);
          return result;
        });
  }

  public TradeChannel getTradeChannel(TradeChannel.Id id) {
    return tradeChannels.computeIfAbsent(
        id,
        k -> {
          TradeChannel result = new TradeChannel(k, getRequestIdGenerator());
          attach(result);
          return result;
        });
  }
}
