package io.contek.invoker.binanceinverse.api.websocket.market;

import io.contek.invoker.commons.websocket.IWebSocketChannel;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface IMarketWebSocketApi {

  IWebSocketChannel<AggTradeEvent> getAggTradeChannel(String symbol);

  IWebSocketChannel<BookTickerEvent> getBookTickerChannel(String symbol);

  IWebSocketChannel<DepthUpdateEvent> getDepthDiffChannel(String symbol, String interval);

  IWebSocketChannel<DepthUpdateEvent> getDepthPartialChannel(
      String symbol, int levels, @Nullable String interval);

  IWebSocketChannel<ForceOrderEvent> getForceOrderChannel(String symbol);

  IWebSocketChannel<MarkPriceUpdateEvent> getMarkPriceChannel(String symbol, String interval);

  IWebSocketChannel<TradeEvent> getTradeChannel(String symbol);
}
