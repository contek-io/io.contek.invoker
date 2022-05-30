package io.contek.invoker.ursa.core.api;

import com.google.common.util.concurrent.ListeningScheduledExecutorService;

import java.time.Duration;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;

import static com.google.common.util.concurrent.MoreExecutors.listeningDecorator;
import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public final class SlidingLimiter {

  private final RateLimit limit;
  private final Semaphore semaphore;
  private final ListeningScheduledExecutorService scheduler;

  public SlidingLimiter(RateLimit limit) {
    this.limit = limit;
    semaphore = new Semaphore(limit.getPermits(), true);
    scheduler = createScheduler();
  }

  private static ListeningScheduledExecutorService createScheduler() {
    ThreadFactory factory =
        target -> {
          Thread thread = new Thread(target);
          thread.setDaemon(true);
          return thread;
        };
    return listeningDecorator(newSingleThreadScheduledExecutor(factory));
  }

  public RateLimit getLimit() {
    return limit;
  }

  public IPermitSession acquire(int permits)
      throws PermitCapExceedException, AcquireTimeoutException, InterruptedException {
    return acquire(permits, null);
  }

  public IPermitSession acquire(int permits, Duration timeout)
      throws PermitCapExceedException, AcquireTimeoutException, InterruptedException {
    if (permits > limit.getPermits()) {
      throw new PermitCapExceedException(permits, limit.getPermits());
    }

    if (permits < 0) {
      throw new NegativePermitException(permits);
    }

    if (permits == 0) {
      return ZeroPermitSession.getInstance();
    }

    if (timeout == null) {
      semaphore.acquire(permits);
    } else {
      boolean acquired = semaphore.tryAcquire(permits, timeout.toNanos(), NANOSECONDS);
      if (!acquired) {
        throw new AcquireTimeoutException(timeout);
      }
    }

    return new SimplePermitSession(canceled -> onSessionClose(permits, canceled));
  }

  private void onSessionClose(int permits, boolean immediate) {
    if (immediate) {
      semaphore.release(permits);
      return;
    }

    scheduler.schedule(() -> semaphore.release(permits), limit.getPeriod());
  }
}
