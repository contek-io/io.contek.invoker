package io.contek.invoker.bitmex.api.websocket.market;

import io.contek.invoker.bitmex.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<LiquidationChannel.Id, LiquidationChannel> liquidationChannels =
      new ConcurrentHashMap<>();
  private final Map<OrderBookL2Channel.Id, OrderBookL2Channel> orderBookL2Channels =
      new ConcurrentHashMap<>();
  private final Map<QuoteChannel.Id, QuoteChannel> quoteChannels = new ConcurrentHashMap<>();
  private final Map<InstrumentChannel.Id, InstrumentChannel> instrumentChannels =
      new ConcurrentHashMap<>();
  private final Map<TradeChannel.Id, TradeChannel> tradeChannels = new ConcurrentHashMap<>();
  private final Map<TradeBinChannel.Id, TradeBinChannel> tradeBinChannels =
      new ConcurrentHashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public LiquidationChannel getLiquidationChannel(LiquidationChannel.Id id) {
    return liquidationChannels.computeIfAbsent(
        id,
        k -> {
          LiquidationChannel result = new LiquidationChannel(k);
          attach(result);
          return result;
        });
  }

  public OrderBookL2Channel getOrderBookL2Channel(OrderBookL2Channel.Id id) {
    return orderBookL2Channels.computeIfAbsent(
        id,
        k -> {
          OrderBookL2Channel result = new OrderBookL2Channel(k);
          attach(result);
          return result;
        });
  }

  public QuoteChannel getQuoteChannel(QuoteChannel.Id id) {
    return quoteChannels.computeIfAbsent(
        id,
        k -> {
          QuoteChannel result = new QuoteChannel(k);
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

  public TradeBinChannel getTradeBinChannel(TradeBinChannel.Id id) {
    return tradeBinChannels.computeIfAbsent(
        id,
        k -> {
          TradeBinChannel result = new TradeBinChannel(k);
          attach(result);
          return result;
        });
  }

  public InstrumentChannel getInstrumentChannel(InstrumentChannel.Id id) {
    return instrumentChannels.computeIfAbsent(
        id,
        k -> {
          InstrumentChannel result = new InstrumentChannel(k);
          attach(result);
          return result;
        });
  }
}
