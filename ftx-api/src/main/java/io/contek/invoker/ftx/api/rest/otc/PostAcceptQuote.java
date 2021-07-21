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
public class PostAcceptQuote extends OTCQuotesRestRequest<PostAcceptQuote.Response> {

    private Integer quoteId;

    public PostAcceptQuote(IActor actor, RestContext context) {
        super(actor, context);
    }

    public Integer getQuoteId() {
        return quoteId;
    }

    public PostAcceptQuote setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
        return this;
    }

    @Override
    protected RestMethod getMethod() {
        return RestMethod.POST;
    }

    @Override
    protected String getEndpointPathOTC() {
        Objects.requireNonNull(quoteId);
        return "/" + quoteId + "accept";
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
    public static final class Response extends RestResponse<_Quote> {
    }
}
