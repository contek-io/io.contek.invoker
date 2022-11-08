package io.contek.invoker.binanceinverse.api.websocket.market.combined;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binanceinverse.api.ApiFactory;
import io.contek.invoker.binanceinverse.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binanceinverse.api.websocket.market.IMarketWebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.security.ICredential;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketCombinedWebSocketApi extends BaseWebSocketApi
    implements IMarketWebSocketApi {

  private final WebSocketContext context;
  private final WebSocketRequestIdGenerator requestIdGenerator = new WebSocketRequestIdGenerator();

  private final Map<BookTickerChannel.Id, BookTickerChannel> bookTickerChannels = new HashMap<>();
  private final Map<TradeChannel.Id, TradeChannel> tradeChannels = new HashMap<>();
  private final Map<AggTradeChannel.Id, AggTradeChannel> aggTradeChannels = new HashMap<>();
  private final Map<DepthDiffChannel.Id, DepthDiffChannel> depthDiffChannels = new HashMap<>();
  private final Map<DepthPartialChannel.Id, DepthPartialChannel> depthPartialChannels =
      new HashMap<>();
  private final Map<ForceOrderChannel.Id, ForceOrderChannel> forceOrderChannels = new HashMap<>();

  public MarketCombinedWebSocketApi(IActor actor, WebSocketContext context) {
    super(
        actor,
        MarketCombinedMessageParser.getInstance(),
        IWebSocketAuthenticator.noOp(),
        IWebSocketLiveKeeper.noOp());
    this.context = context;
  }

  @Override
  public BookTickerChannel getBookTickerChannel(String symbol) {
    synchronized (bookTickerChannels) {
      return bookTickerChannels.computeIfAbsent(
          BookTickerChannel.Id.of(symbol),
          k -> {
            BookTickerChannel result = new BookTickerChannel(k, requestIdGenerator);
            attach(result);
            return result;
          });
    }
  }

  @Override
  public TradeChannel getTradeChannel(String symbol) {
    synchronized (tradeChannels) {
      return tradeChannels.computeIfAbsent(
          TradeChannel.Id.of(symbol),
          k -> {
            TradeChannel result = new TradeChannel(k, requestIdGenerator);
            attach(result);
            return result;
          });
    }
  }

  @Override
  public AggTradeChannel getAggTradeChannel(String symbol) {
    synchronized (aggTradeChannels) {
      return aggTradeChannels.computeIfAbsent(
          AggTradeChannel.Id.of(symbol),
          k -> {
            AggTradeChannel result = new AggTradeChannel(k, requestIdGenerator);
            attach(result);
            return result;
          });
    }
  }

  @Override
  public DepthDiffChannel getDepthDiffChannel(String symbol, String interval) {
    synchronized (depthDiffChannels) {
      return depthDiffChannels.computeIfAbsent(
          DepthDiffChannel.Id.of(symbol, interval),
          k -> {
            DepthDiffChannel result = new DepthDiffChannel(k, requestIdGenerator);
            attach(result);
            return result;
          });
    }
  }

  @Override
  public DepthPartialChannel getDepthPartialChannel(
      String symbol, int levels, @Nullable String interval) {
    synchronized (depthPartialChannels) {
      return depthPartialChannels.computeIfAbsent(
          DepthPartialChannel.Id.of(symbol, levels, interval),
          k -> {
            DepthPartialChannel result = new DepthPartialChannel(k, requestIdGenerator);
            attach(result);
            return result;
          });
    }
  }

  @Override
  public ForceOrderChannel getForceOrderChannel(String symbol) {
    synchronized (forceOrderChannels) {
      return forceOrderChannels.computeIfAbsent(
          ForceOrderChannel.Id.of(symbol),
          k -> {
            ForceOrderChannel result = new ForceOrderChannel(k, requestIdGenerator);
            attach(result);
            return result;
          });
    }
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/stream");
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ApiFactory.RateLimits.ONE_WEB_SOCKET_CONNECTION;
  }

  @Override
  protected void checkErrorMessage(AnyWebSocketMessage message) throws WebSocketRuntimeException {}
}
