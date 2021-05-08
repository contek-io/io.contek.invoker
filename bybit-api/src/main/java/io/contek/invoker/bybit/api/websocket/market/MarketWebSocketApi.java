package io.contek.invoker.bybit.api.websocket.market;

import io.contek.invoker.bybit.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<OrderBook25Channel.Id, OrderBook25Channel> orderBook25Channels =
      new HashMap<>();
  private final Map<OrderBook200Channel.Id, OrderBook200Channel> orderBook200Channels =
      new HashMap<>();
  private final Map<TradeChannel.Id, TradeChannel> tradeChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public OrderBook25Channel getOrderBook25Channel(OrderBook25Channel.Id id) {
    synchronized (orderBook25Channels) {
      return orderBook25Channels.computeIfAbsent(
          id,
          k -> {
            OrderBook25Channel result = new OrderBook25Channel(k);
            attach(result);
            return result;
          });
    }
  }

  public OrderBook200Channel getOrderBook200Channel(OrderBook200Channel.Id id) {
    synchronized (orderBook200Channels) {
      return orderBook200Channels.computeIfAbsent(
          id,
          k -> {
            OrderBook200Channel result = new OrderBook200Channel(k);
            attach(result);
            return result;
          });
    }
  }

  public TradeChannel getTradeChannel(TradeChannel.Id id) {
    synchronized (tradeChannels) {
      return tradeChannels.computeIfAbsent(
          id,
          k -> {
            TradeChannel result = new TradeChannel(k);
            attach(result);
            return result;
          });
    }
  }
}
