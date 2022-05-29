package io.contek.invoker.ftx.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._FutureStats;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class GetFutureStats extends MarketRestRequest<GetFutureStats.Response> {

    private String futureName;

    GetFutureStats(IActor actor, RestContext context) {
        super(actor, context);
    }

    public GetFutureStats setFutureName(String futureName) {
        this.futureName = futureName;
        return this;
    }

    public String getFutureName() {
        return futureName;
    }

    @Override
    protected String getEndpointPath() {
        return "/api/futures/" + requireNonNull(futureName) + "/stats";
    }

    @Override
    protected RestParams getParams() {
        return RestParams.empty();
    }

    @Override
    protected Class<GetFutureStats.Response> getResponseType() {
        return GetFutureStats.Response.class;
    }

    public static final class Response extends RestResponse<_FutureStats> {}
}
