package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.ftx.api.websocket.WebSocketApi;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<OrderBookChannel.Topic, OrderBookChannel> orderBookChannels = new HashMap<>();
  private final Map<TickerChannel.Topic, TickerChannel> tickerChannels = new HashMap<>();
  private final Map<TradesChannel.Topic, TradesChannel> tradesChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public OrderBookChannel getOrderBookChannel(OrderBookChannel.Topic topic) {
    synchronized (orderBookChannels) {
      return orderBookChannels.computeIfAbsent(
          topic,
          k -> {
            OrderBookChannel result = new OrderBookChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public TickerChannel getTickerChannel(TickerChannel.Topic topic) {
    synchronized (tickerChannels) {
      return tickerChannels.computeIfAbsent(
          topic,
          k -> {
            TickerChannel result = new TickerChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public TradesChannel getTradesChannel(TradesChannel.Topic topic) {
    synchronized (tradesChannels) {
      return tradesChannels.computeIfAbsent(
          topic,
          k -> {
            TradesChannel result = new TradesChannel(k);
            attach(result);
            return result;
          });
    }
  }
}
