package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.hbdmlinear.api.common._Depth;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class MarketDepthChannel extends WebSocketMarketChannel<MarketDepthChannel.Message> {

  MarketDepthChannel(
      String contractCode, String type, WebSocketMarketRequestIdGenerator requestIdGenerator) {
    super(
        String.format("market.%s.depth.%s", contractCode, type),
        MarketDepthChannel.Message.class,
        requestIdGenerator);
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTickMessage<_Depth> {}
}
