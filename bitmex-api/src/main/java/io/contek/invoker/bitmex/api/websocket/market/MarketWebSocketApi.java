package io.contek.invoker.bitmex.api.websocket.market;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import io.contek.invoker.bitmex.api.websocket.WebSocketApi;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.websocket.WebSocketContext;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<String, LiquidationChannel> liquidationChannels = new HashMap<>();
  private final Map<String, OrderBookL2Channel> orderBookL2Channels = new HashMap<>();
  private final Map<String, QuoteChannel> quoteChannels = new HashMap<>();
  private final Map<String, TradeChannel> tradeChannels = new HashMap<>();
  private final Table<String, String, TradeBinChannel> tradeBinChannels = HashBasedTable.create();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public LiquidationChannel getLiquidationChannel(String instrument) {
    synchronized (liquidationChannels) {
      return liquidationChannels.computeIfAbsent(
          instrument,
          k -> {
            LiquidationChannel result = new LiquidationChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public OrderBookL2Channel getOrderBookL2Channel(String instrument) {
    synchronized (orderBookL2Channels) {
      return orderBookL2Channels.computeIfAbsent(
          instrument,
          k -> {
            OrderBookL2Channel result = new OrderBookL2Channel(k);
            attach(result);
            return result;
          });
    }
  }

  public QuoteChannel getQuoteChannel(String instrument) {
    synchronized (quoteChannels) {
      return quoteChannels.computeIfAbsent(
          instrument,
          k -> {
            QuoteChannel result = new QuoteChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public TradeChannel getTradeChannel(String instrument) {
    synchronized (quoteChannels) {
      return tradeChannels.computeIfAbsent(
          instrument,
          k -> {
            TradeChannel result = new TradeChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public TradeBinChannel getTradeBinChannel(String binSize, String instrument) {
    synchronized (tradeBinChannels) {
      TradeBinChannel result = tradeBinChannels.get(binSize, instrument);
      if (result == null) {
        result = new TradeBinChannel(binSize, instrument);
        attach(result);
        tradeBinChannels.put(binSize, instrument, result);
      }
      return result;
    }
  }
}
