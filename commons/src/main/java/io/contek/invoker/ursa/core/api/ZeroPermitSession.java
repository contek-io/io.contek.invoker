package io.contek.invoker.ursa.core.api;

public final class ZeroPermitSession implements IPermitSession {

  private ZeroPermitSession() {}

  public static ZeroPermitSession getInstance() {
    return Holder.INSTANCE;
  }

  @Override
  public void cancel() {}

  @Override
  public void close() {}

  private static final class Holder {

    private static final ZeroPermitSession INSTANCE = new ZeroPermitSession();
  }
}
