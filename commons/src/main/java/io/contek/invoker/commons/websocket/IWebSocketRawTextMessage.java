package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public interface IWebSocketRawTextMessage {

  String getRawText();
}
