package io.contek.invoker.ftx.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._OptionOpenInterest;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Objects;

@NotThreadSafe
public final class GetOptionOpenInterest extends MarketRestRequest<GetOptionOpenInterest.Response> {

    private String symbol;

    GetOptionOpenInterest(IActor actor, RestContext context) {
        super(actor, context);
    }

    public String getSymbol() {
        return symbol;
    }

    public GetOptionOpenInterest setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    @Override
    protected String getEndpointPath() {
        Objects.requireNonNull(symbol);
        return "/api/options/open_interest/" + symbol;
    }

    @Override
    protected RestParams getParams() {
        return RestParams.empty();
    }

    @Override
    protected Class<GetOptionOpenInterest.Response> getResponseType() {
        return GetOptionOpenInterest.Response.class;
    }

    @NotThreadSafe
    public static final class Response extends RestResponse<_OptionOpenInterest> {
    }
}
