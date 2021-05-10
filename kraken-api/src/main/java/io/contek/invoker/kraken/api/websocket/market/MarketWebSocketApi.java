package io.contek.invoker.kraken.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.kraken.api.websocket.WebSocketApi;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<BookChannel.Topic, BookChannel> bookChannels = new HashMap<>();
  private final Map<TradeChannel.Topic, TradeChannel> tradeChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public BookChannel getOrderBookChannel(BookChannel.Topic topic) {
    synchronized (bookChannels) {
      return bookChannels.computeIfAbsent(
          topic,
          k -> {
            BookChannel result = new BookChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }

  public TradeChannel getTradesChannel(TradeChannel.Topic topic) {
    synchronized (tradeChannels) {
      return tradeChannels.computeIfAbsent(
          topic,
          k -> {
            TradeChannel result = new TradeChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }
}
