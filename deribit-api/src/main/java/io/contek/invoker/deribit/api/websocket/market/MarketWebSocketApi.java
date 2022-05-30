package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.deribit.api.websocket.WebSocketApi;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<BookChangeChannel.Id, BookChangeChannel> bookChangeChannels =
      new ConcurrentHashMap<>();
  private final Map<BookSnapshotChannel.Id, BookSnapshotChannel> bookSnapshotChannels =
      new ConcurrentHashMap<>();
  private final Map<TradesChannel.Id, TradesChannel> tradesChannels = new ConcurrentHashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public BookChangeChannel getBookChangeChannel(BookChangeChannel.Id id) {
    return bookChangeChannels.computeIfAbsent(
        id,
        k -> {
          BookChangeChannel result = new BookChangeChannel(k, getRequestIdGenerator());
          attach(result);
          return result;
        });
  }

  public BookSnapshotChannel getBookSnapshotChannel(BookSnapshotChannel.Id id) {
    return bookSnapshotChannels.computeIfAbsent(
        id,
        k -> {
          BookSnapshotChannel result = new BookSnapshotChannel(k, getRequestIdGenerator());
          attach(result);
          return result;
        });
  }

  public TradesChannel getTradesChannel(TradesChannel.Id id) {
    return tradesChannels.computeIfAbsent(
        id,
        k -> {
          TradesChannel result = new TradesChannel(k, getRequestIdGenerator());
          attach(result);
          return result;
        });
  }
}
