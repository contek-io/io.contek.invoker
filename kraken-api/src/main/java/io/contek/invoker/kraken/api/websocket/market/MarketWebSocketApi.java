package io.contek.invoker.kraken.api.websocket.market;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.kraken.api.websocket.WebSocketApi;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketWebSocketApi extends WebSocketApi {

  private final Table<String, Integer, OrderBookChannel> orderBookChannels =
      HashBasedTable.create();
  private final Map<String, TradesChannel> tradesChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public OrderBookChannel getOrderBookChannel(String pair, int depth) {
    synchronized (orderBookChannels) {
      OrderBookChannel result = orderBookChannels.get(pair, depth);
      if (result == null) {
        result = new OrderBookChannel(pair, depth);
        attach(result);
        orderBookChannels.put(pair, depth, result);
      }
      return result;
    }
  }

  public TradesChannel getTradesChannel(String symbol) {
    synchronized (tradesChannels) {
      return tradesChannels.computeIfAbsent(
          symbol,
          k -> {
            TradesChannel result = new TradesChannel(k);
            attach(result);
            return result;
          });
    }
  }
}
