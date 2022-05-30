package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.ftx.api.websocket.WebSocketApi;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<OrderBookChannel.Id, OrderBookChannel> orderBookChannels =
      new ConcurrentHashMap<>();
  private final Map<TickerChannel.Id, TickerChannel> tickerChannels = new ConcurrentHashMap<>();
  private final Map<TradesChannel.Id, TradesChannel> tradesChannels = new ConcurrentHashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public OrderBookChannel getOrderBookChannel(OrderBookChannel.Id id) {
    return orderBookChannels.computeIfAbsent(
        id,
        k -> {
          OrderBookChannel result = new OrderBookChannel(k);
          attach(result);
          return result;
        });
  }

  public TickerChannel getTickerChannel(TickerChannel.Id id) {
    return tickerChannels.computeIfAbsent(
        id,
        k -> {
          TickerChannel result = new TickerChannel(k);
          attach(result);
          return result;
        });
  }

  public TradesChannel getTradesChannel(TradesChannel.Id id) {
    return tradesChannels.computeIfAbsent(
        id,
        k -> {
          TradesChannel result = new TradesChannel(k);
          attach(result);
          return result;
        });
  }
}
