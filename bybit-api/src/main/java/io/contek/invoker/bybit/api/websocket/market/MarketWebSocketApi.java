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
  private final Map<InstrumentInfoChannel.Id, InstrumentInfoChannel> instrumentInfoChannels =
      new HashMap<>();
  private final Map<KlineV2Channel.Id, KlineV2Channel> klineV2Channels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public OrderBook25Channel getOrderBook25Channel(String symbol) {
    synchronized (orderBook25Channels) {
      return orderBook25Channels.computeIfAbsent(
          OrderBook25Channel.Id.of(symbol),
          k -> {
            OrderBook25Channel result = new OrderBook25Channel(k);
            attach(result);
            return result;
          });
    }
  }

  public OrderBook200Channel getOrderBook200Channel(String symbol) {
    synchronized (orderBook200Channels) {
      return orderBook200Channels.computeIfAbsent(
          OrderBook200Channel.Id.of(symbol),
          k -> {
            OrderBook200Channel result = new OrderBook200Channel(k);
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

  public InstrumentInfoChannel getInstrumentInfoChannel(String symbol) {
    synchronized (instrumentInfoChannels) {
      return instrumentInfoChannels.computeIfAbsent(
          InstrumentInfoChannel.Id.of(symbol),
          k -> {
            InstrumentInfoChannel result = new InstrumentInfoChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public KlineV2Channel getKlineV2Channel(String interval, String symbol) {
    synchronized (klineV2Channels) {
      return klineV2Channels.computeIfAbsent(
          KlineV2Channel.Id.of(interval, symbol),
          k -> {
            KlineV2Channel result = new KlineV2Channel(k);
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
