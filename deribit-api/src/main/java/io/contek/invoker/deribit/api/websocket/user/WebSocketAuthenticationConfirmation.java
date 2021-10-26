package io.contek.invoker.deribit.api.websocket.user;

import io.contek.invoker.deribit.api.common._AuthResult;
import io.contek.invoker.deribit.api.websocket.common.WebSocketResponse;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketAuthenticationConfirmation extends WebSocketResponse<_AuthResult> {}
