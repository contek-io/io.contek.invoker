package io.contek.invoker.bitstamp.api.websocket.market;

import io.contek.invoker.bitstamp.api.websocket.WebSocketApi;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.websocket.WebSocketContext;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<String, DiffOrderBookChannel> diffOrderBookChannels = new HashMap<>();
  private final Map<String, LiveTradesChannel> liveTradesChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public DiffOrderBookChannel getDiffOrderBookChannel(String symbol) {
    synchronized (diffOrderBookChannels) {
      return diffOrderBookChannels.computeIfAbsent(
          symbol,
          k -> {
            DiffOrderBookChannel result = new DiffOrderBookChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public LiveTradesChannel getLiveTradesChannel(String symbol) {
    synchronized (liveTradesChannels) {
      return liveTradesChannels.computeIfAbsent(
          symbol,
          k -> {
            LiveTradesChannel result = new LiveTradesChannel(k);
            attach(result);
            return result;
          });
    }
  }
}
