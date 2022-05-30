package io.contek.invoker.commons.rest;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.RequestContext;
import io.contek.invoker.commons.actor.http.AnyHttpException;
import io.contek.invoker.commons.actor.http.HttpBusyException;
import io.contek.invoker.commons.actor.http.HttpInterruptedException;
import io.contek.invoker.commons.actor.http.IHttpClient;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.json.Json;
import io.contek.invoker.security.ICredential;
import io.contek.invoker.ursa.core.api.AcquireTimeoutException;
import io.vertx.core.Future;

public abstract class BaseRestRequest<R> {

  private final IActor actor;

  protected BaseRestRequest(IActor actor) {
    this.actor = actor;
  }

  public final Future<R> submit() throws AnyHttpException {
    RestCall call = createCall(actor.credential());

    try (RequestContext context = actor.requestContext(getClass().getSimpleName(), getRequiredQuotas())) {

      if (context.client() instanceof IHttpClient.RestClient restClient) {

        return call
          .submit(restClient.webClient())
          .map(event -> Json.decodeValue(event.body(), getResponseType()));
      }
      else {
        throw new RuntimeException("Can call just by IHttpClient.RestClient");
      }

    } catch (AcquireTimeoutException e) {
      throw new HttpBusyException(e);
    } catch (InterruptedException e) {
      throw new HttpInterruptedException(e);
    }
  }

  protected abstract ImmutableList<TypedPermitRequest> getRequiredQuotas();

  protected abstract RestCall createCall(ICredential credential);

  protected abstract Class<R> getResponseType();
}
