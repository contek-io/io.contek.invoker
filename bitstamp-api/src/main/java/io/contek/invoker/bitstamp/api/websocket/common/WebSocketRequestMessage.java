package io.contek.invoker.bitstamp.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Map;

@NotThreadSafe
public final class WebSocketRequestMessage extends WebSocketEventMessage<Map<String, String>> {}
