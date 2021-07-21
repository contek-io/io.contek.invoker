package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class DeleteOrdersByClientOrderId
        extends UserRestRequest<DeleteOrdersByClientOrderId.Response> {

    private String byClientId;

    public DeleteOrdersByClientOrderId(IActor actor, RestContext context) {
        super(actor, context);
    }

    public String getByClientId() {
        return byClientId;
    }

    public DeleteOrdersByClientOrderId setByClientId(String by_client_id) {
        this.byClientId = by_client_id;
        return this;
    }

    @Override
    protected RestMethod getMethod() {
        return RestMethod.DELETE;
    }

    @Override
    protected String getEndpointPath() {
        requireNonNull(byClientId);
        return format("/api/orders/by_client_id/{0}", byClientId);
    }

    @Override
    protected RestParams getParams() {
        return RestParams.empty();
    }

    @Override
    protected Class<Response> getResponseType() {
        return Response.class;
    }

    @NotThreadSafe
    public static final class Response extends RestResponse<String> {
    }
}
