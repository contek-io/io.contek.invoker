package io.contek.invoker.ursa.core.api;

public interface IPermitSession extends AutoCloseable {

  void cancel();

  @Override
  void close();
}
