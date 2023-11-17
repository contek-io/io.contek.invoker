package io.contek.invoker.okx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitRule;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._PlaceOrderAlgoAck;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.time.Duration;

import static io.contek.invoker.commons.actor.ratelimit.LimitType.API_KEY;
import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public class PostTradeOrderAlgo  extends UserRestRequest<PostTradeOrderAlgo.Response>{
    public static final RateLimitRule RATE_LIMIT_RULE =
            RateLimitRule.newBuilder()
                    .setName("api_key_rest_post_trade_order_algo")
                    .setType(API_KEY)
                    .setMaxPermits(20)
                    .setResetPeriod(Duration.ofSeconds(2))
                    .build();

    private static final ImmutableList<TypedPermitRequest> REQUIRED_QUOTA = ImmutableList.of(RATE_LIMIT_RULE.forPermits(1));

    //Common attributes
    private String instId;
    private String tdMode;
    private String ccy;
    private String side;
    private String posSide;
    private String ordType;
    private String sz;
    private String tag;
    private Boolean reduceOnly;
    private String tgtCcy;

    //Stop Order
    private String tpTriggerPx;
    private String tpTriggerPxType;
    private String tpOrdPx;
    private String slTriggerPx;
    private String slTriggerPxType;
    private String slOrdPx;

    //Trigger Order
    private String triggerPx;
    private String triggerPxType;
    private String orderPx;

    //Trailing Stop Order
    private String callbackRatio;
    private String callbackSpread;
    private String activePx;

    PostTradeOrderAlgo(IActor actor, RestContext context) {
        super(actor, context);
    }


    public PostTradeOrderAlgo setInstId(String instId) {
        this.instId = instId;
        return this;
    }

    public PostTradeOrderAlgo setTdMode(String tdMode) {
        this.tdMode = tdMode;
        return this;
    }

    public PostTradeOrderAlgo setCcy(@Nullable String ccy) {
        this.ccy = ccy;
        return this;
    }

    public PostTradeOrderAlgo setSide(String side) {
        this.side = side;
        return this;
    }

    public PostTradeOrderAlgo setPosSide(@Nullable String posSide) {
        this.posSide = posSide;
        return this;
    }

    public PostTradeOrderAlgo setOrdType(String ordType) {
        this.ordType = ordType;
        return this;
    }

    public PostTradeOrderAlgo setSz(String sz) {
        this.sz = sz;
        return this;
    }

    public PostTradeOrderAlgo setTag(@Nullable String tag) {
        this.tag = tag;
        return this;
    }

    public PostTradeOrderAlgo setReduceOnly(@Nullable Boolean reduceOnly) {
        this.reduceOnly = reduceOnly;
        return this;
    }

    public PostTradeOrderAlgo setTgtCcy(@Nullable String tgtCcy) {
        this.tgtCcy = tgtCcy;
        return this;
    }

    public PostTradeOrderAlgo setTpTriggerPx(@Nullable String tpTriggerPx) {
        this.tpTriggerPx = tpTriggerPx;
        return this;
    }

    public PostTradeOrderAlgo setTpTriggerPxType(@Nullable String tpTriggerPxType) {
        this.tpTriggerPxType = tpTriggerPxType;
        return this;
    }

    public PostTradeOrderAlgo setTpOrdPx(@Nullable String tpOrdPx) {
        this.tpOrdPx = tpOrdPx;
        return this;
    }

    public PostTradeOrderAlgo setSlTriggerPx(@Nullable String slTriggerPx) {
        this.slTriggerPx = slTriggerPx;
        return this;
    }

    public PostTradeOrderAlgo setSlTriggerPxType(@Nullable String slTriggerPxType) {
        this.slTriggerPxType = slTriggerPxType;
        return this;
    }

    public PostTradeOrderAlgo setSlOrdPx(@Nullable String slOrdPx) {
        this.slOrdPx = slOrdPx;
        return this;
    }

    public PostTradeOrderAlgo setTriggerPx(@Nullable String triggerPx) {
        this.triggerPx = triggerPx;
        return this;
    }

    public PostTradeOrderAlgo setTriggerPxType(@Nullable String triggerPxType) {
        this.triggerPxType = triggerPxType;
        return this;
    }

    public PostTradeOrderAlgo setOrderPx(@Nullable String orderPx) {
        this.orderPx = orderPx;
        return this;
    }

    public PostTradeOrderAlgo setCallbackRatio(@Nullable String callbackRatio) {
        this.callbackRatio = callbackRatio;
        return this;
    }

    public PostTradeOrderAlgo setCallbackSpread(@Nullable String callbackSpread) {
        this.callbackSpread = callbackSpread;
        return this;
    }

    public PostTradeOrderAlgo setActivePx(@Nullable String activePx) {
        this.activePx = activePx;
        return this;
    }

    @Override
    protected RestMethod getMethod() {
        return POST;
    }

    @Override
    protected String getEndpointPath() {
        return "/api/v5/trade/order-algo";
    }

    @Override
    protected RestParams getParams() {
        RestParams.Builder builder = RestParams.newBuilder();

        requireNonNull(instId);
        builder.add("instId", instId);

        requireNonNull(tdMode);
        builder.add("tdMode", tdMode);

        requireNonNull(side);
        builder.add("side", side);

        requireNonNull(ordType);
        builder.add("ordType", ordType);

        requireNonNull(sz);
        builder.add("sz", sz);

        if (ccy != null) {
            builder.add("ccy", ccy);
        }

        if (tag != null) {
            builder.add("tag", tag);
        }

        if (posSide != null) {
            builder.add("posSide", posSide);
        }

        if (reduceOnly != null) {
            builder.add("reduceOnly", reduceOnly);
        }

        if (tgtCcy != null) {
            builder.add("tgtCcy", tgtCcy);
        }

        if(reduceOnly != null){
            builder.add("reduceOnly", reduceOnly);
        }

        //Stop order
        if(tpTriggerPx != null){
            builder.add("tpTriggerPx", tpTriggerPx);
        }

        if(tpTriggerPxType != null){
            builder.add("tpTriggerPxType", tpTriggerPxType);
        }

        if(tpOrdPx != null){
            builder.add("tpOrdPx", tpOrdPx);
        }

        if(slTriggerPx != null){
            builder.add("slTriggerPx", slTriggerPx);
        }

        if(slTriggerPxType != null){
            builder.add("slTriggerPxType", slTriggerPxType);
        }

        if(slOrdPx != null){
            builder.add("slOrdPx", slOrdPx);
        }

        // Trigger order
        if(triggerPx != null){
            builder.add("triggerPx", triggerPx);
        }

        if(triggerPx != null){
            builder.add("triggerPxType", triggerPxType);
        }

        if(orderPx != null){
            builder.add("orderPx", orderPx);
        }

        //Trailing order
        if(callbackRatio != null){
            builder.add("callbackRatio", callbackRatio);
        }

        if(callbackSpread != null){
            builder.add("callbackSpread", callbackSpread);
        }

        if(activePx != null){
            builder.add("activePx", activePx);
        }

        return builder.build();
    }


    @Override
    protected Class<PostTradeOrderAlgo.Response> getResponseType() {
        return PostTradeOrderAlgo.Response.class;
    }

    @Override
    protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
        return REQUIRED_QUOTA;
    }

    @NotThreadSafe
    public static final class Response extends RestResponse<_PlaceOrderAlgoAck> {}
}
