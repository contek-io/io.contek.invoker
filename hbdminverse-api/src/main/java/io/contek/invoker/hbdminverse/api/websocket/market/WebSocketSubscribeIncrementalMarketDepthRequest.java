package io.contek.invoker.hbdminverse.api.websocket.market;

import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketSubscribeRequest;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketSubscribeIncrementalMarketDepthRequest
    extends WebSocketSubscribeRequest {

  public String data_type;
}
