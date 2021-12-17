package io.contek.invoker.bybitlinear.api.websocket.market;

import io.contek.invoker.bybitlinear.api.websocket.WebSocketApi;
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

  public InstrumentInfoChannel getInstrumentInfoChannel(InstrumentInfoChannel.Id id) {
    synchronized (instrumentInfoChannels) {
      return instrumentInfoChannels.computeIfAbsent(
          id,
          k -> {
            InstrumentInfoChannel result = new InstrumentInfoChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public KlineV2Channel getKlineV2Channel(KlineV2Channel.Id id) {
    synchronized (klineV2Channels) {
      return klineV2Channels.computeIfAbsent(
          id,
          k -> {
            KlineV2Channel result = new KlineV2Channel(k);
            attach(result);
            return result;
          });
    }
  }
}
