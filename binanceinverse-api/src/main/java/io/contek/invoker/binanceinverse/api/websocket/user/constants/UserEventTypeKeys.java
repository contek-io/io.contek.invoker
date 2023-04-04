package io.contek.invoker.binanceinverse.api.websocket.user.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class UserEventTypeKeys {

  public static final String _listenKeyExpired = "listenKeyExpired";

  public static final String _MARGIN_CALL = "MARGIN_CALL";

  public static final String _ACCOUNT_UPDATE = "ACCOUNT_UPDATE";

  public static final String _ORDER_TRADE_UPDATE = "ORDER_TRADE_UPDATE";

  public static final String _ACCOUNT_CONFIG_UPDATE = "ACCOUNT_CONFIG_UPDATE";

  private UserEventTypeKeys() {}
}
