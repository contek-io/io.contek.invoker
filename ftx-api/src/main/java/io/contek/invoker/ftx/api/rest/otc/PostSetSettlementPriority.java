package io.contek.invoker.ftx.api.rest.otc;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Quote;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Objects;

@NotThreadSafe
public class PostSetSettlementPriority extends OTCQuotesRestRequest<PostSetSettlementPriority.Response> {

    private Integer quoteId;
    private Integer settlementPriority;

    public PostSetSettlementPriority(IActor actor, RestContext context) {
        super(actor, context);
    }

    public Integer getQuoteId() {
        return quoteId;
    }

    public PostSetSettlementPriority setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
        return this;
    }

    public Integer getSettlementPriority() {
        return settlementPriority;
    }

    public PostSetSettlementPriority setSettlementPriority(Integer settlementPriority) {
        this.settlementPriority = settlementPriority;
        return this;
    }

    @Override
    protected RestMethod getMethod() {
        return RestMethod.POST;
    }

    @Override
    protected String getEndpointPathOTC() {
        Objects.requireNonNull(quoteId);
        return "/" + quoteId + "/set_settlement_priority";
    }

    @Override
    protected RestParams getParams() {
        if (settlementPriority != null) {
            return RestParams.newBuilder()
                    .add("settlementPriority", settlementPriority)
                    .build();
        }
        return RestParams.empty();
    }

    @Override
    protected Class<Response> getResponseType() {
        return Response.class;
    }

    @NotThreadSafe
    public static final class Response extends RestResponse<_Quote> {
    }
}
