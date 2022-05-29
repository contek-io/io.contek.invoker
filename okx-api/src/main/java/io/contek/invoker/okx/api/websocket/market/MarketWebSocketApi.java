package io.contek.invoker.okx.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketCall;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.okx.api.websocket.WebSocketApi;
import io.contek.invoker.security.ICredential;

import java.util.HashMap;
import java.util.Map;

public final class MarketWebSocketApi extends WebSocketApi {

  private final WebSocketContext context;

  private final Map<OrderBookChannel.Id, OrderBookChannel> orderBookChannels = new HashMap<>();
  private final Map<TickersChannel.Id, TickersChannel> tickerChannels = new HashMap<>();
  private final Map<TradesChannel.Id, TradesChannel> tradesChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor);
    this.context = context;
  }

  public OrderBookChannel getOrderBookChannel(OrderBookChannel.Id id) {
    synchronized (orderBookChannels) {
      return orderBookChannels.computeIfAbsent(
          id,
          k -> {
            OrderBookChannel result = new OrderBookChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public TickersChannel getTickerChannel(TickersChannel.Id id) {
    synchronized (tickerChannels) {
      return tickerChannels.computeIfAbsent(
          id,
          k -> {
            TickersChannel result = new TickersChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public TradesChannel getTradesChannel(TradesChannel.Id id) {
    synchronized (tradesChannels) {
      return tradesChannels.computeIfAbsent(
          id,
          k -> {
            TradesChannel result = new TradesChannel(k);
            attach(result);
            return result;
          });
    }
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/ws/v5/public");
  }
}
