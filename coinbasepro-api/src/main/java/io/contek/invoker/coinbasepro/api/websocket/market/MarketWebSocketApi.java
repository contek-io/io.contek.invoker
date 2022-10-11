package io.contek.invoker.coinbasepro.api.websocket.market;

import io.contek.invoker.coinbasepro.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<Level2Channel.Id, Level2Channel> level2Channels = new HashMap<>();
  private final Map<MatchesChannel.Id, MatchesChannel> matchesChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public Level2Channel getLevel2Channel(String productId) {
    synchronized (level2Channels) {
      return level2Channels.computeIfAbsent(
          Level2Channel.Id.of(productId),
          k -> {
            Level2Channel result = new Level2Channel(k);
            attach(result);
            return result;
          });
    }
  }

  public MatchesChannel getMatchesChannel(String productId) {
    synchronized (matchesChannels) {
      return matchesChannels.computeIfAbsent(
          MatchesChannel.Id.of(productId),
          k -> {
            MatchesChannel result = new MatchesChannel(k);
            attach(result);
            return result;
          });
    }
  }
}
