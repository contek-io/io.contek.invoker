package io.contek.invoker.ftx.api.rest.spotmargin;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;

import javax.annotation.concurrent.ThreadSafe;

/**
 * https://docs.ftx.com/#spot-margin
 */
@ThreadSafe
public final class SpotMarginApi {

    private final IActor actor;
    private final RestContext context;

    public SpotMarginApi(IActor actor, RestContext context) {
        this.actor = actor;
        this.context = context;
    }

    public GetLendingInfo getLendingInfo() {
        return new GetLendingInfo(actor, context);
    }

    public GetLendingOffers getLendingOffers() {
        return new GetLendingOffers(actor, context);
    }

    public PostLendingOffer postLendingOffer(final LendingOffer lendingOffer) {
        return new PostLendingOffer(actor, context, lendingOffer);
    }
}
