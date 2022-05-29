package io.contek.invoker.bitstamp.api.websocket.market;

import io.contek.invoker.bitstamp.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import java.util.HashMap;
import java.util.Map;

public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<DiffOrderBookChannel.Id, DiffOrderBookChannel> diffOrderBookChannels =
      new HashMap<>();
  private final Map<LiveTradesChannel.Id, LiveTradesChannel> liveTradesChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public DiffOrderBookChannel getDiffOrderBookChannel(DiffOrderBookChannel.Id id) {
    synchronized (diffOrderBookChannels) {
      return diffOrderBookChannels.computeIfAbsent(
          id,
          k -> {
            DiffOrderBookChannel result = new DiffOrderBookChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public LiveTradesChannel getLiveTradesChannel(LiveTradesChannel.Id id) {
    synchronized (liveTradesChannels) {
      return liveTradesChannels.computeIfAbsent(
          id,
          k -> {
            LiveTradesChannel result = new LiveTradesChannel(k);
            attach(result);
            return result;
          });
    }
  }
}
