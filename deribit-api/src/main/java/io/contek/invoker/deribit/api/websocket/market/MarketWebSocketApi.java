package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.deribit.api.websocket.WebSocketApi;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<BookChangeChannel.Id, BookChangeChannel> bookChangeChannels = new HashMap<>();
  private final Map<BookSnapshotChannel.Id, BookSnapshotChannel> bookSnapshotChannels =
      new HashMap<>();
  private final Map<TradesChannel.Id, TradesChannel> tradesChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public BookChangeChannel getBookChangeChannel(String instrumentName, String interval) {
    synchronized (bookChangeChannels) {
      return bookChangeChannels.computeIfAbsent(
          BookChangeChannel.Id.of(instrumentName, interval),
          k -> {
            BookChangeChannel result = new BookChangeChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }

  public BookSnapshotChannel getBookSnapshotChannel(
      String instrumentName, String group, int depth, String interval) {
    synchronized (bookSnapshotChannels) {
      return bookSnapshotChannels.computeIfAbsent(
          BookSnapshotChannel.Id.of(instrumentName, group, depth, interval),
          k -> {
            BookSnapshotChannel result = new BookSnapshotChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }

  public TradesChannel getTradesChannel(String instrumentName, String interval) {
    synchronized (tradesChannels) {
      return tradesChannels.computeIfAbsent(
          TradesChannel.Id.of(instrumentName, interval),
          k -> {
            TradesChannel result = new TradesChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }
}
