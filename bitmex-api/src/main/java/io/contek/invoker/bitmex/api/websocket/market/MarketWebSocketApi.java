package io.contek.invoker.bitmex.api.websocket.market;

import io.contek.invoker.bitmex.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
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

  public LiquidationChannel getLiquidationChannel(String instrument) {
    synchronized (liquidationChannels) {
      return liquidationChannels.computeIfAbsent(
          LiquidationChannel.Id.of(instrument),
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
          OrderBookL2Channel.Id.of(instrument),
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
          QuoteChannel.Id.of(instrument),
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
          TradeChannel.Id.of(instrument),
          k -> {
            TradeChannel result = new TradeChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public TradeBinChannel getTradeBinChannel(String binSize, String instrument) {
    synchronized (tradeBinChannels) {
      return tradeBinChannels.computeIfAbsent(
          TradeBinChannel.Id.of(binSize, instrument),
          k -> {
            TradeBinChannel result = new TradeBinChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public InstrumentChannel getInstrumentChannel(String instrument) {
    synchronized (instrumentChannels) {
      return instrumentChannels.computeIfAbsent(
          InstrumentChannel.Id.of(instrument),
          k -> {
            InstrumentChannel result = new InstrumentChannel(k);
            attach(result);
            return result;
          });
    }
  }
}
