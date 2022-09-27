package io.contek.invoker.commons.rest;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.RequestContext;
import io.contek.invoker.commons.actor.http.AnyHttpException;
import io.contek.invoker.commons.actor.http.HttpBusyException;
import io.contek.invoker.commons.actor.http.HttpInterruptedException;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.security.ICredential;
import io.contek.ursa.AcquireTimeoutException;

import javax.annotation.concurrent.NotThreadSafe;

import static java.util.Objects.requireNonNull;

@NotThreadSafe
public abstract class BaseRestRequest<R> {

  private final IActor actor;

  protected BaseRestRequest(IActor actor) {
    this.actor = actor;
  }

  public final R submit() throws AnyHttpException {
    RestCall call = createCall(actor.getCredential());

    try (RequestContext context =
        actor.getRequestContext(getClass().getSimpleName(), getRequiredQuotas())) {
      RestResponse response = call.submit(context.getClient());
      R result = requireNonNull(response.getAs(getResponseType()));
      checkResult(result, response);
      return result;
    } catch (AcquireTimeoutException e) {
      throw new HttpBusyException(e);
    } catch (InterruptedException e) {
      throw new HttpInterruptedException(e);
    }
  }

  protected abstract ImmutableList<TypedPermitRequest> getRequiredQuotas();

  protected abstract RestCall createCall(ICredential credential);

  protected abstract Class<R> getResponseType();

  protected abstract void checkResult(R result, RestResponse response) throws AnyHttpException;
}
