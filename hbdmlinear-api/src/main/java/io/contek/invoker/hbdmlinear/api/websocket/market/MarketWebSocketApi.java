package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.hbdmlinear.api.websocket.common.marketdata.MarketDataWebSocketApi;

import java.util.HashMap;
import java.util.Map;

public final class MarketWebSocketApi extends MarketDataWebSocketApi {

  private final Map<IncrementalDepthChannel.Id, IncrementalDepthChannel> incrementalDepthChannels =
      new HashMap<>();
  private final Map<TradeDetailChannel.Id, TradeDetailChannel> tradeDetailChannels =
      new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public IncrementalDepthChannel getIncrementalDepthChannel(IncrementalDepthChannel.Id id) {
    synchronized (incrementalDepthChannels) {
      return incrementalDepthChannels.computeIfAbsent(
          id,
          k -> {
            IncrementalDepthChannel result =
                new IncrementalDepthChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }

  public TradeDetailChannel getTradeDetailChannel(TradeDetailChannel.Id id) {
    synchronized (tradeDetailChannels) {
      return tradeDetailChannels.computeIfAbsent(
          id,
          k -> {
            TradeDetailChannel result = new TradeDetailChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }
}
