package io.contek.invoker.hbdmlinear.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.hbdmlinear.api.rest.RestRequest;

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
