package io.contek.invoker.ftx.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Market;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Objects;

@NotThreadSafe
public final class GetSingleMarket extends MarketRestRequest<GetSingleMarket.Response> {

    private String market;

    GetSingleMarket(IActor actor, RestContext context) {
        super(actor, context);
    }

    public String getMarket() {
        return market;
    }

    public GetSingleMarket setMarket(String market) {
        this.market = market;
        return this;
    }

    @Override
    protected String getEndpointPath() {
        Objects.requireNonNull(market);
        return "/api/markets/" + market;
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
    public static final class Response extends RestResponse<_Market> {
    }
}
