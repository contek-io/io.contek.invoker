package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.deribit.api.websocket.WebSocketApi;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<BookChannel.Id, BookChannel> bookChannels = new HashMap<>();
  private final Map<ChartTradesChannel.Id, ChartTradesChannel> chartTradesChannels = new HashMap<>();
  private final Map<TradesChannel.Id, TradesChannel> tradesChannels = new HashMap<>();
  private final Map<TickerChannel.Id, TickerChannel> tickerChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public BookChannel getBookChannel(BookChannel.Id id) {
    synchronized (bookChannels) {
      return bookChannels.computeIfAbsent(
          id,
          k -> {
            BookChannel result = new BookChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }

  public ChartTradesChannel getChartTradesChannel(ChartTradesChannel.Id id) {
    synchronized (chartTradesChannels) {
      return chartTradesChannels.computeIfAbsent(
          id,
          k -> {
            ChartTradesChannel result = new ChartTradesChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }

  public TradesChannel getTradesChannel(TradesChannel.Id id) {
    synchronized (tradesChannels) {
      return tradesChannels.computeIfAbsent(
          id,
          k -> {
            TradesChannel result = new TradesChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }

  public TickerChannel getTickerChannel(TickerChannel.Id id) {
    synchronized (tickerChannels) {
      return tickerChannels.computeIfAbsent(
          id,
          k -> {
            TickerChannel result = new TickerChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }
}
