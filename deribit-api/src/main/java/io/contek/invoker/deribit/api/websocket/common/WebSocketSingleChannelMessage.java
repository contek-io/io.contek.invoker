package io.contek.invoker.deribit.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketSingleChannelMessage<T>
    extends WebSocketChannelMessage<SingleSubscriptionParams<T>> {}
