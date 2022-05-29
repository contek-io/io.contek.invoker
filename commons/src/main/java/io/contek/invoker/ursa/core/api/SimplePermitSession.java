package io.contek.invoker.ursa.core.api;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

final class SimplePermitSession implements IPermitSession {

  private final Consumer<Boolean> onClose;
  private final AtomicBoolean canceled = new AtomicBoolean(false);
  private final AtomicBoolean closed = new AtomicBoolean(false);

  SimplePermitSession(Consumer<Boolean> onClose) {
    this.onClose = onClose;
  }

  @Override
  public void cancel() {
    canceled.set(true);
  }

  @Override
  public void close() {
    if (closed.getAndSet(true)) {
      return;
    }

    onClose.accept(canceled.get());
  }
}
