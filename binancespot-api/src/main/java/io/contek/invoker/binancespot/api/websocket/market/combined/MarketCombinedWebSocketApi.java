package io.contek.invoker.binancespot.api.websocket.market.combined;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancespot.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketCombinedWebSocketApi extends BaseWebSocketApi {

  private final WebSocketContext context;
  private final WebSocketRequestIdGenerator requestIdGenerator = new WebSocketRequestIdGenerator();

  private final Map<BookTickerChannel.Id, BookTickerChannel> bookTickerChannels = new HashMap<>();
  private final Map<TradeChannel.Id, TradeChannel> tradeChannels = new HashMap<>();
  private final Map<AggTradeChannel.Id, AggTradeChannel> aggTradeChannels = new HashMap<>();
  private final Map<DepthDiffChannel.Id, DepthDiffChannel> depthDiffChannels = new HashMap<>();
  private final Map<DepthPartialChannel.Id, DepthPartialChannel> depthPartialChannels =
      new HashMap<>();

  public MarketCombinedWebSocketApi(IActor actor, WebSocketContext context) {
    super(
        actor,
        MarketCombinedMessageParser.getInstance(),
        IWebSocketAuthenticator.noOp(),
        IWebSocketLiveKeeper.noOp());
    this.context = context;
  }

  public BookTickerChannel getBookTickerChannel(BookTickerChannel.Id id) {
    synchronized (bookTickerChannels) {
      return bookTickerChannels.computeIfAbsent(
          id,
          k -> {
            BookTickerChannel result = new BookTickerChannel(k, requestIdGenerator);
            attach(result);
            return result;
          });
    }
  }

  public TradeChannel getTradeChannel(TradeChannel.Id id) {
    synchronized (tradeChannels) {
      return tradeChannels.computeIfAbsent(
          id,
          k -> {
            TradeChannel result = new TradeChannel(k, requestIdGenerator);
            attach(result);
            return result;
          });
    }
  }

  public AggTradeChannel getAggTradeChannel(AggTradeChannel.Id id) {
    synchronized (aggTradeChannels) {
      return aggTradeChannels.computeIfAbsent(
          id,
          k -> {
            AggTradeChannel result = new AggTradeChannel(k, requestIdGenerator);
            attach(result);
            return result;
          });
    }
  }

  public DepthPartialChannel getDepthPartialChannel(DepthPartialChannel.Id id) {
    synchronized (depthPartialChannels) {
      return depthPartialChannels.computeIfAbsent(
          id,
          k -> {
            DepthPartialChannel result = new DepthPartialChannel(k, requestIdGenerator);
            attach(result);
            return result;
          });
    }
  }

  public DepthDiffChannel getDepthDiffChannel(DepthDiffChannel.Id id) {
    synchronized (depthDiffChannels) {
      return depthDiffChannels.computeIfAbsent(
          id,
          k -> {
            DepthDiffChannel result = new DepthDiffChannel(k, requestIdGenerator);
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
    return ImmutableList.of();
  }

  @Override
  protected void checkErrorMessage(AnyWebSocketMessage message) throws WebSocketRuntimeException {}
}
