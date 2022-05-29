package io.contek.invoker.bitmex.api.websocket.market;

import io.contek.invoker.bitmex.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import java.util.HashMap;
import java.util.Map;

public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<LiquidationChannel.Id, LiquidationChannel> liquidationChannels =
      new HashMap<>();
  private final Map<OrderBookL2Channel.Id, OrderBookL2Channel> orderBookL2Channels =
      new HashMap<>();
  private final Map<QuoteChannel.Id, QuoteChannel> quoteChannels = new HashMap<>();
  private final Map<InstrumentChannel.Id, InstrumentChannel> instrumentChannels = new HashMap<>();
  private final Map<TradeChannel.Id, TradeChannel> tradeChannels = new HashMap<>();
  private final Map<TradeBinChannel.Id, TradeBinChannel> tradeBinChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public LiquidationChannel getLiquidationChannel(LiquidationChannel.Id id) {
    synchronized (liquidationChannels) {
      return liquidationChannels.computeIfAbsent(
          id,
          k -> {
            LiquidationChannel result = new LiquidationChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public OrderBookL2Channel getOrderBookL2Channel(OrderBookL2Channel.Id id) {
    synchronized (orderBookL2Channels) {
      return orderBookL2Channels.computeIfAbsent(
          id,
          k -> {
            OrderBookL2Channel result = new OrderBookL2Channel(k);
            attach(result);
            return result;
          });
    }
  }

  public QuoteChannel getQuoteChannel(QuoteChannel.Id id) {
    synchronized (quoteChannels) {
      return quoteChannels.computeIfAbsent(
          id,
          k -> {
            QuoteChannel result = new QuoteChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public TradeChannel getTradeChannel(TradeChannel.Id id) {
    synchronized (quoteChannels) {
      return tradeChannels.computeIfAbsent(
          id,
          k -> {
            TradeChannel result = new TradeChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public TradeBinChannel getTradeBinChannel(TradeBinChannel.Id id) {
    synchronized (tradeBinChannels) {
      return tradeBinChannels.computeIfAbsent(
          id,
          k -> {
            TradeBinChannel result = new TradeBinChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public InstrumentChannel getInstrumentChannel(InstrumentChannel.Id id) {
    synchronized (instrumentChannels) {
      return instrumentChannels.computeIfAbsent(
          id,
          k -> {
            InstrumentChannel result = new InstrumentChannel(k);
            attach(result);
            return result;
          });
    }
  }
}
