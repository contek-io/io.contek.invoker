package io.contek.invoker.hbdmlinear.api.websocket.user.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OpKeys {

  public static final String _ping = "ping";

  public static final String _pong = "pong";

  public static final String _auth = "auth";

  public static final String _sub = "sub";

  public static final String _unsub = "unsub";

  public static final String _notify = "notify";

  public static final String _close = "close";

  private OpKeys() {}
}
