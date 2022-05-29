package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.deribit.api.websocket.WebSocketApi;

import java.util.HashMap;
import java.util.Map;

public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<BookChangeChannel.Id, BookChangeChannel> bookChangeChannels = new HashMap<>();
  private final Map<BookSnapshotChannel.Id, BookSnapshotChannel> bookSnapshotChannels =
      new HashMap<>();
  private final Map<TradesChannel.Id, TradesChannel> tradesChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public BookChangeChannel getBookChangeChannel(BookChangeChannel.Id id) {
    synchronized (bookChangeChannels) {
      return bookChangeChannels.computeIfAbsent(
          id,
          k -> {
            BookChangeChannel result = new BookChangeChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }

  public BookSnapshotChannel getBookSnapshotChannel(BookSnapshotChannel.Id id) {
    synchronized (bookSnapshotChannels) {
      return bookSnapshotChannels.computeIfAbsent(
          id,
          k -> {
            BookSnapshotChannel result = new BookSnapshotChannel(k, getRequestIdGenerator());
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
}
