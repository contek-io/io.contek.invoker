package io.contek.invoker.binancespot.api.websocket.market;

import io.contek.invoker.commons.websocket.IWebSocketChannel;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface IMarketWebSocketApi {

  IWebSocketChannel<AggTradeEvent> getAggTradeChannel(String symbol);

  IWebSocketChannel<BookTickerEvent> getBookTickerChannel(String symbol);

  IWebSocketChannel<DepthDiffEvent> getDepthDiffChannel(String symbol, String interval);

  IWebSocketChannel<DepthPartialEvent> getDepthPartialChannel(
      String symbol, int levels, @Nullable String interval);

  IWebSocketChannel<TradeEvent> getTradeChannel(String symbol);
}
