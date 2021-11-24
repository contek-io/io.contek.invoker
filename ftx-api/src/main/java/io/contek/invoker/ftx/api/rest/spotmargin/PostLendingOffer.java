package io.contek.invoker.ftx.api.rest.spotmargin;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.ftx.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostLendingOffer extends SpotMarginRestRequest<PostLendingOffer.Response> {

    private LendingOffer lendingOffer;

    PostLendingOffer(final IActor actor, final RestContext context, final LendingOffer lendingOffer) {
        super(actor, context);
        this.lendingOffer = lendingOffer;
    }

    @Override
    protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
        return ONE_REST_REQUEST;
    }

    @Override
    protected Class<PostLendingOffer.Response> getResponseType() {
        return Response.class;
    }

    @Override
    protected RestMethod getMethod() {
        return RestMethod.POST;
    }

    @Override
    protected String getEndpointPath() {
        return "/api/spot_margin/offers";
    }

    @Override
    protected RestParams getParams() {
        RestParams.Builder builder = RestParams.newBuilder();

        final String coin = lendingOffer.coin;
        requireNonNull(coin);
        builder.add("coin", coin);

        final double rate = lendingOffer.rate;
        requireNonNull(rate);
        builder.add("rate", rate);

        final double size = lendingOffer.size;
        requireNonNull(size);
        builder.add("size", size);

        return builder.build();
    }

    @NotThreadSafe
    public static final class Response extends RestResponse<Void> {}
}
