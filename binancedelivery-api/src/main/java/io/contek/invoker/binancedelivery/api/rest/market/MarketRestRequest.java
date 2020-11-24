package io.contek.invoker.binancedelivery.api.rest.market;

import io.contek.invoker.binancedelivery.api.rest.RestRequest;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestMethod;

import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.api.rest.RestMethod.GET;

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
