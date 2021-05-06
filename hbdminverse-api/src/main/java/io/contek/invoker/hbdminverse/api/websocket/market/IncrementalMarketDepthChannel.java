package io.contek.invoker.hbdminverse.api.websocket.market;

import io.contek.invoker.hbdminverse.api.common._Depth;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class IncrementalMarketDepthChannel
    extends WebSocketMarketChannel<IncrementalMarketDepthChannel.Message> {

  IncrementalMarketDepthChannel(
      String contractCode, int size, WebSocketMarketRequestIdGenerator requestIdGenerator) {
    super(
        String.format("market.%s.depth.size_%d.high_freq", contractCode, size),
        IncrementalMarketDepthChannel.Message.class,
        requestIdGenerator);
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTickMessage<Tick> {}

  @NotThreadSafe
  public static final class Tick extends _Depth {

    public String event;
  }
}
