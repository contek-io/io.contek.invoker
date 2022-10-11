package io.contek.invoker.bitstamp.api.websocket.market;

import io.contek.invoker.bitstamp.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<DiffOrderBookChannel.Id, DiffOrderBookChannel> diffOrderBookChannels =
      new HashMap<>();
  private final Map<LiveTradesChannel.Id, LiveTradesChannel> liveTradesChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public DiffOrderBookChannel getDiffOrderBookChannel(String currencyPair) {
    synchronized (diffOrderBookChannels) {
      return diffOrderBookChannels.computeIfAbsent(
          DiffOrderBookChannel.Id.of(currencyPair),
          k -> {
            DiffOrderBookChannel result = new DiffOrderBookChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public LiveTradesChannel getLiveTradesChannel(String currencyPair) {
    synchronized (liveTradesChannels) {
      return liveTradesChannels.computeIfAbsent(
          LiveTradesChannel.Id.of(currencyPair),
          k -> {
            LiveTradesChannel result = new LiveTradesChannel(k);
            attach(result);
            return result;
          });
    }
  }
}
