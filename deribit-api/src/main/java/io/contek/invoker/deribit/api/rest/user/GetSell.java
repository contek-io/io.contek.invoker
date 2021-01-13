package io.contek.invoker.deribit.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;

public final class GetSell extends PlaceOrderRequest {
    GetSell(IActor actor, RestContext context) {
        super(actor, context);
    }

    @Override
    protected String getEndpointPath() {
        return "/api/v2/private/buy";
    }
}
