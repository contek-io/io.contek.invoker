package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.ftx.api.rest.RestRequest;

import static com.google.common.base.Preconditions.checkArgument;

abstract class SpotMarginRestRequest<T> extends RestRequest<T> {
    SpotMarginRestRequest(IActor actor, RestContext context) {
        super(actor, context);
        checkArgument(!actor.getCredential().isAnonymous());
    }

    @Override
    protected String getEndpointPath() {
        return "/api/spot_margin/" + getEndpointPathSpotMargin();
    }

    protected abstract String getEndpointPathSpotMargin();
}
