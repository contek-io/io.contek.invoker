package io.contek.invoker.bybit.api.websocket.market;

import io.contek.invoker.bybit.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<OrderBook25Channel.Id, OrderBook25Channel> orderBook25Channels =
      new ConcurrentHashMap<>();
  private final Map<OrderBook200Channel.Id, OrderBook200Channel> orderBook200Channels =
      new ConcurrentHashMap<>();
  private final Map<TradeChannel.Id, TradeChannel> tradeChannels = new ConcurrentHashMap<>();
  private final Map<InstrumentInfoChannel.Id, InstrumentInfoChannel> instrumentInfoChannels =
      new ConcurrentHashMap<>();
  private final Map<KlineV2Channel.Id, KlineV2Channel> klineV2Channels = new ConcurrentHashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public OrderBook25Channel getOrderBook25Channel(OrderBook25Channel.Id id) {
    return orderBook25Channels.computeIfAbsent(
        id,
        k -> {
          OrderBook25Channel result = new OrderBook25Channel(k);
          attach(result);
          return result;
        });
  }

  public OrderBook200Channel getOrderBook200Channel(OrderBook200Channel.Id id) {
    return orderBook200Channels.computeIfAbsent(
        id,
        k -> {
          OrderBook200Channel result = new OrderBook200Channel(k);
          attach(result);
          return result;
        });
  }

  public TradeChannel getTradeChannel(TradeChannel.Id id) {
    return tradeChannels.computeIfAbsent(
        id,
        k -> {
          TradeChannel result = new TradeChannel(k);
          attach(result);
          return result;
        });
  }

  public InstrumentInfoChannel getInstrumentInfoChannel(InstrumentInfoChannel.Id id) {
    return instrumentInfoChannels.computeIfAbsent(
        id,
        k -> {
          InstrumentInfoChannel result = new InstrumentInfoChannel(k);
          attach(result);
          return result;
        });
  }

  public KlineV2Channel getKlineV2Channel(KlineV2Channel.Id id) {
    return klineV2Channels.computeIfAbsent(
        id,
        k -> {
          KlineV2Channel result = new KlineV2Channel(k);
          attach(result);
          return result;
        });
  }
}
