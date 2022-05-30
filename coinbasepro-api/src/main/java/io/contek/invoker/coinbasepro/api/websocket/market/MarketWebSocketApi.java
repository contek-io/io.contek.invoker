package io.contek.invoker.coinbasepro.api.websocket.market;

import io.contek.invoker.coinbasepro.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<Level2Channel.Id, Level2Channel> level2Channels = new ConcurrentHashMap<>();
  private final Map<MatchesChannel.Id, MatchesChannel> matchesChannels = new ConcurrentHashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public Level2Channel getLevel2Channel(Level2Channel.Id id) {
    return level2Channels.computeIfAbsent(
        id,
        k -> {
          Level2Channel result = new Level2Channel(k);
          attach(result);
          return result;
        });
  }

  public MatchesChannel getMatchesChannel(MatchesChannel.Id id) {
    return matchesChannels.computeIfAbsent(
        id,
        k -> {
          MatchesChannel result = new MatchesChannel(k);
          attach(result);
          return result;
        });
  }
}
