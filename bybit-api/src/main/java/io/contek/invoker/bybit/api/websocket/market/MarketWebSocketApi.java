package io.contek.invoker.bybit.api.websocket.market;

import io.contek.invoker.bybit.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<OrderbookChannel.Id, OrderbookChannel> orderbookChannels = new HashMap<>();
  private final Map<TradeChannel.Id, TradeChannel> tradeChannels = new HashMap<>();
  private final Map<TickerChannel.Id, TickerChannel> tickerChannels = new HashMap<>();
  private final Map<KlineChannel.Id, KlineChannel> klineChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public OrderbookChannel getOrderbookChannel(int depth, String symbol) {
    synchronized (orderbookChannels) {
      return orderbookChannels.computeIfAbsent(
          OrderbookChannel.Id.of(depth, symbol),
          k -> {
            OrderbookChannel result = new OrderbookChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public TradeChannel getTradeChannel(String symbol) {
    synchronized (tradeChannels) {
      return tradeChannels.computeIfAbsent(
          TradeChannel.Id.of(symbol),
          k -> {
            TradeChannel result = new TradeChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public TickerChannel getTickerChannel(String symbol) {
    synchronized (tickerChannels) {
      return tickerChannels.computeIfAbsent(
          TickerChannel.Id.of(symbol),
          k -> {
            TickerChannel result = new TickerChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public KlineChannel getKlineChannel(String interval, String symbol) {
    synchronized (klineChannels) {
      return klineChannels.computeIfAbsent(
          KlineChannel.Id.of(interval, symbol),
          k -> {
            KlineChannel result = new KlineChannel(k);
            attach(result);
            return result;
          });
    }
  }

  @Override
  protected String getUrlSuffix() {
    return "/realtime_public";
  }
}
