package io.contek.invoker.hbdminverse.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.hbdminverse.api.websocket.common.marketdata.MarketDataWebSocketApi;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketWebSocketApi extends MarketDataWebSocketApi {

  private final Map<IncrementalDepthChannel.Id, IncrementalDepthChannel> incrementalDepthChannels =
      new HashMap<>();
  private final Map<TradeDetailChannel.Id, TradeDetailChannel> tradeDetailChannels =
      new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public IncrementalDepthChannel getIncrementalDepthChannel(String contractCode, int size) {
    synchronized (incrementalDepthChannels) {
      return incrementalDepthChannels.computeIfAbsent(
          IncrementalDepthChannel.Id.of(contractCode, size),
          k -> {
            IncrementalDepthChannel result =
                new IncrementalDepthChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }

  public TradeDetailChannel getTradeDetailChannel(String contractCode) {
    synchronized (tradeDetailChannels) {
      return tradeDetailChannels.computeIfAbsent(
          TradeDetailChannel.Id.of(contractCode),
          k -> {
            TradeDetailChannel result = new TradeDetailChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }
}
