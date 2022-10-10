package io.contek.invoker.binancefutures.api.websocket.market.direct;

import io.contek.invoker.binancefutures.api.websocket.market.*;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.IWebSocketChannel;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketDirectWebSocketApi implements IMarketWebSocketApi {

  private final IActor actor;
  private final WebSocketContext context;

  private final Map<BookTickerDirectStream.Id, BookTickerDirectStream> bookTickerChannels =
      new HashMap<>();
  private final Map<TradeDirectStream.Id, TradeDirectStream> tradeChannels = new HashMap<>();
  private final Map<AggTradeDirectStream.Id, AggTradeDirectStream> aggTradeChannels =
      new HashMap<>();
  private final Map<DepthDiffDirectStream.Id, DepthDiffDirectStream> depthDiffChannels =
      new HashMap<>();
  private final Map<DepthPartialDirectStream.Id, DepthPartialDirectStream> depthPartialChannels =
      new HashMap<>();
  private final Map<ForceOrderDirectStream.Id, ForceOrderDirectStream> forceOrderChannels =
      new HashMap<>();

  public MarketDirectWebSocketApi(IActor actor, WebSocketContext context) {
    this.actor = actor;
    this.context = context;
  }

  public IWebSocketChannel<BookTickerEvent> getBookTickerChannel(String symbol) {
    synchronized (bookTickerChannels) {
      return bookTickerChannels
          .computeIfAbsent(
              BookTickerDirectStream.Id.of(symbol),
              k -> new BookTickerDirectStream(k, actor, context))
          .getChannel();
    }
  }

  public IWebSocketChannel<TradeEvent> getTradeChannel(String symbol) {
    synchronized (tradeChannels) {
      return tradeChannels
          .computeIfAbsent(
              TradeDirectStream.Id.of(symbol), k -> new TradeDirectStream(k, actor, context))
          .getChannel();
    }
  }

  public IWebSocketChannel<AggTradeEvent> getAggTradeChannel(String symbol) {
    synchronized (aggTradeChannels) {
      return aggTradeChannels
          .computeIfAbsent(
              AggTradeDirectStream.Id.of(symbol), k -> new AggTradeDirectStream(k, actor, context))
          .getChannel();
    }
  }

  public IWebSocketChannel<DepthUpdateEvent> getDepthDiffChannel(String symbol, String interval) {
    synchronized (depthDiffChannels) {
      return depthDiffChannels
          .computeIfAbsent(
              DepthDiffDirectStream.Id.of(symbol, interval),
              k -> new DepthDiffDirectStream(k, actor, context))
          .getChannel();
    }
  }

  public IWebSocketChannel<DepthUpdateEvent> getDepthPartialChannel(
      String symbol, int levels, @Nullable String interval) {
    synchronized (depthPartialChannels) {
      return depthPartialChannels
          .computeIfAbsent(
              DepthPartialDirectStream.Id.of(symbol, levels, interval),
              k -> new DepthPartialDirectStream(k, actor, context))
          .getChannel();
    }
  }

  public IWebSocketChannel<ForceOrderEvent> getForceOrderChannel(String symbol) {
    synchronized (forceOrderChannels) {
      return forceOrderChannels
          .computeIfAbsent(
              ForceOrderDirectStream.Id.of(symbol),
              k -> new ForceOrderDirectStream(k, actor, context))
          .getChannel();
    }
  }
}
