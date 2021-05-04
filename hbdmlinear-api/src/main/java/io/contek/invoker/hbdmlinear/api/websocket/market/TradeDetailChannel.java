package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.hbdmlinear.api.common._TradeDetail;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class TradeDetailChannel extends WebSocketMarketChannel<TradeDetailChannel.Message> {

  TradeDetailChannel(String contractCode, WebSocketMarketRequestIdGenerator requestIdGenerator) {
    super("market." + contractCode + ".trade.detail", TradeDetailChannel.Message.class, requestIdGenerator);
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTickMessage<_TradeDetail> {}
}
