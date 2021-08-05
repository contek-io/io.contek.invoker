package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._FundingPayment;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import java.util.List;

@NotThreadSafe
public final class GetFundingPayments extends UserRestRequest<GetFundingPayments.Response> {

    public GetFundingPayments(IActor actor, RestContext context) {
        super(actor, context);
    }

    @Override
    protected RestMethod getMethod() {
        return RestMethod.GET;
    }

    @Override
    protected String getEndpointPath() {
        return "/api/funding_payments";
    }

    @Override
    protected RestParams getParams() {
        return RestParams.empty();
    }

    @Override
    protected Class<GetFundingPayments.Response> getResponseType() {
        return GetFundingPayments.Response.class;
    }

    @NotThreadSafe
    public static final class Response extends RestResponse<List<_FundingPayment>> {}
}
