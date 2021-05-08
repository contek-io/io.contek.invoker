package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.deribit.api.websocket.WebSocketApi;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<BookChannel.Id, BookChannel> orderBookChannels = new HashMap<>();
  private final Map<TradesChannel.Id, TradesChannel> tradesChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public BookChannel getOrderBookChannel(BookChannel.Id topic) {
    synchronized (orderBookChannels) {
      return orderBookChannels.computeIfAbsent(
          topic,
          k -> {
            BookChannel result = new BookChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public TradesChannel getTradesChannel(TradesChannel.Id topic) {
    synchronized (tradesChannels) {
      return tradesChannels.computeIfAbsent(
          topic,
          k -> {
            TradesChannel result = new TradesChannel(k);
            attach(result);
            return result;
          });
    }
  }
}
