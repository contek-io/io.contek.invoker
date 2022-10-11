package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.ftx.api.websocket.WebSocketApi;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<OrderBookChannel.Id, OrderBookChannel> orderBookChannels = new HashMap<>();
  private final Map<TickerChannel.Id, TickerChannel> tickerChannels = new HashMap<>();
  private final Map<TradesChannel.Id, TradesChannel> tradesChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public OrderBookChannel getOrderBookChannel(String market) {
    synchronized (orderBookChannels) {
      return orderBookChannels.computeIfAbsent(
          OrderBookChannel.Id.of(market),
          k -> {
            OrderBookChannel result = new OrderBookChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public TickerChannel getTickerChannel(String market) {
    synchronized (tickerChannels) {
      return tickerChannels.computeIfAbsent(
          TickerChannel.Id.of(market),
          k -> {
            TickerChannel result = new TickerChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public TradesChannel getTradesChannel(String market) {
    synchronized (tradesChannels) {
      return tradesChannels.computeIfAbsent(
          TradesChannel.Id.of(market),
          k -> {
            TradesChannel result = new TradesChannel(k);
            attach(result);
            return result;
          });
    }
  }
}
