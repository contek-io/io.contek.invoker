package io.contek.invoker.ftx.api.rest.otc;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Quote;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class GetRecentAcceptedQuotes extends OTCQuotesRestRequest<GetRecentAcceptedQuotes.Response> {

    private Boolean settledImmediately;
    private Boolean fullySettled;
    private Integer before;
    private Double limit;

    public GetRecentAcceptedQuotes(IActor actor, RestContext context) {
        super(actor, context);
    }

    public Boolean getSettledImmediately() {
        return settledImmediately;
    }

    public GetRecentAcceptedQuotes setSettledImmediately(Boolean settledImmediately) {
        this.settledImmediately = settledImmediately;
        return this;
    }

    public Boolean getFullySettled() {
        return fullySettled;
    }

    public GetRecentAcceptedQuotes setFullySettled(Boolean fullySettled) {
        this.fullySettled = fullySettled;
        return this;
    }

    public Integer getBefore() {
        return before;
    }

    public GetRecentAcceptedQuotes setBefore(Integer before) {
        this.before = before;
        return this;
    }

    public Double getLimit() {
        return limit;
    }

    public GetRecentAcceptedQuotes setLimit(Double limit) {
        this.limit = limit;
        return this;
    }

    @Override
    protected RestMethod getMethod() {
        return RestMethod.GET;
    }

    @Override
    protected String getEndpointPathOTC() {
        return "/accepted";
    }

    @Override
    protected RestParams getParams() {
        RestParams.Builder builder = RestParams.newBuilder();

        if (settledImmediately != null) {
            builder.add("settledImmediately", settledImmediately);
        }

        if (fullySettled != null) {
            builder.add("fullySettled", fullySettled);
        }

        if (before != null) {
            builder.add("before", before);
        }

        if (limit != null) {
            builder.add("limit", limit);
        }
        return builder.build();
    }

    @Override
    protected Class<Response> getResponseType() {
        return Response.class;
    }

    @NotThreadSafe
    public static final class Response extends RestResponse<List<_Quote>> {
    }
}
