package io.contek.invoker.bitstamp.api.websocket.market;

import io.contek.invoker.bitstamp.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<DiffOrderBookChannel.Id, DiffOrderBookChannel> diffOrderBookChannels =
      new ConcurrentHashMap<>();
  private final Map<LiveTradesChannel.Id, LiveTradesChannel> liveTradesChannels =
      new ConcurrentHashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public DiffOrderBookChannel getDiffOrderBookChannel(DiffOrderBookChannel.Id id) {
    return diffOrderBookChannels.computeIfAbsent(
        id,
        k -> {
          DiffOrderBookChannel result = new DiffOrderBookChannel(k);
          attach(result);
          return result;
        });
  }

  public LiveTradesChannel getLiveTradesChannel(LiveTradesChannel.Id id) {
    return liveTradesChannels.computeIfAbsent(
        id,
        k -> {
          LiveTradesChannel result = new LiveTradesChannel(k);
          attach(result);
          return result;
        });
  }
}
