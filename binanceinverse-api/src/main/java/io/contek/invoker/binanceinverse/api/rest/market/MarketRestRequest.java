package io.contek.invoker.binanceinverse.api.rest.market;

import io.contek.invoker.binanceinverse.api.rest.RestRequest;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;

import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.GET;

@ThreadSafe
abstract class MarketRestRequest<T> extends RestRequest<T> {

  MarketRestRequest(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected final RestMethod getMethod() {
    return GET;
  }
}
