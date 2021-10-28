package io.contek.invoker.deribit.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class WebSocketUnsubscribeConfirmation extends WebSocketResponse<List<String>> {}
