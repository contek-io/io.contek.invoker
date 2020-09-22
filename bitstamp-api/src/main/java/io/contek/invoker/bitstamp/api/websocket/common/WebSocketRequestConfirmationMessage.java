package io.contek.invoker.bitstamp.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Map;

@NotThreadSafe
public final class WebSocketRequestConfirmationMessage
    extends WebSocketChannelMessage<Map<String, String>> {}
