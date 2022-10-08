package io.contek.invoker.binancefutures.api.websocket.market.raw;

import io.contek.invoker.binancefutures.api.websocket.market.*;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.IWebSocketChannel;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketRawWebSocketApi implements IMarketWebSocketApi {

  private final IActor actor;
  private final WebSocketContext context;

  private final Map<BookTickerRawStream.Id, BookTickerRawStream> bookTickerChannels =
      new HashMap<>();
  private final Map<TradeRawStream.Id, TradeRawStream> tradeChannels = new HashMap<>();
  private final Map<AggTradeRawStream.Id, AggTradeRawStream> aggTradeChannels = new HashMap<>();
  private final Map<DepthDiffRawStream.Id, DepthDiffRawStream> depthDiffChannels = new HashMap<>();
  private final Map<DepthPartialRawStream.Id, DepthPartialRawStream> depthPartialChannels =
      new HashMap<>();
  private final Map<ForceOrderRawStream.Id, ForceOrderRawStream> forceOrderChannels =
      new HashMap<>();

  public MarketRawWebSocketApi(IActor actor, WebSocketContext context) {
    this.actor = actor;
    this.context = context;
  }

  public IWebSocketChannel<BookTickerEvent> getBookTickerChannel(String symbol) {
    synchronized (bookTickerChannels) {
      return bookTickerChannels
          .computeIfAbsent(
              BookTickerRawStream.Id.of(symbol), k -> new BookTickerRawStream(k, actor, context))
          .getChannel();
    }
  }

  public IWebSocketChannel<TradeEvent> getTradeChannel(String symbol) {
    synchronized (tradeChannels) {
      return tradeChannels
          .computeIfAbsent(TradeRawStream.Id.of(symbol), k -> new TradeRawStream(k, actor, context))
          .getChannel();
    }
  }

  public IWebSocketChannel<AggTradeEvent> getAggTradeChannel(String symbol) {
    synchronized (aggTradeChannels) {
      return aggTradeChannels
          .computeIfAbsent(
              AggTradeRawStream.Id.of(symbol), k -> new AggTradeRawStream(k, actor, context))
          .getChannel();
    }
  }

  public IWebSocketChannel<DepthUpdateEvent> getDepthDiffChannel(String symbol, String interval) {
    synchronized (depthDiffChannels) {
      return depthDiffChannels
          .computeIfAbsent(
              DepthDiffRawStream.Id.of(symbol, interval),
              k -> new DepthDiffRawStream(k, actor, context))
          .getChannel();
    }
  }

  public IWebSocketChannel<DepthUpdateEvent> getDepthPartialChannel(
      String symbol, int levels, @Nullable String interval) {
    synchronized (depthPartialChannels) {
      return depthPartialChannels
          .computeIfAbsent(
              DepthPartialRawStream.Id.of(symbol, levels, interval),
              k -> new DepthPartialRawStream(k, actor, context))
          .getChannel();
    }
  }

  public IWebSocketChannel<ForceOrderEvent> getForceOrderChannel(String symbol) {
    synchronized (forceOrderChannels) {
      return forceOrderChannels
          .computeIfAbsent(
              ForceOrderRawStream.Id.of(symbol), k -> new ForceOrderRawStream(k, actor, context))
          .getChannel();
    }
  }
}
