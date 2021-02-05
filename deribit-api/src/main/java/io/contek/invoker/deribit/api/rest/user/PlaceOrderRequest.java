package io.contek.invoker.deribit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.deribit.api.common._PlaceOrderResponse;
import io.contek.invoker.deribit.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.GET;
import static io.contek.invoker.deribit.api.ApiFactory.RateLimits.ONE_MATCHING_ENGINE_REQUEST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public abstract class PlaceOrderRequest extends UserRestRequest<PlaceOrderRequest.Response> {

  private String instrument_name;
  private Double amount;
  private String type = "limit";
  private String label;
  private Double price;
  private String time_in_force;
  private Double max_show;
  private Boolean post_only;
  private Boolean reject_post_only;
  private Boolean reduce_only;
  private Double stop_price;
  private String trigger;
  private String advanced;
  private Boolean mmp;

  PlaceOrderRequest(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PlaceOrderRequest setInstrument_name(String instrument_name) {
    this.instrument_name = instrument_name;
    return this;
  }


  public PlaceOrderRequest setAmount(Double amount) {
    this.amount = amount;
    return this;
  }

  public PlaceOrderRequest setType(String type) {
    this.type = type;
    return this;
  }

  public PlaceOrderRequest setLabel(String label) {
    this.label = label;
    return this;
  }

  public PlaceOrderRequest setPrice(double price) {
    this.price = price;
    return this;
  }

  public PlaceOrderRequest setTimeInForce(String timeInForce) {
    this.time_in_force = timeInForce;
    return this;
  }

  public PlaceOrderRequest setMaxShow(double maxShow) {
    this.max_show = maxShow;
    return this;
  }


  public PlaceOrderRequest setPostOnly(Boolean postOnly) {
    this.post_only = postOnly;
    return this;
  }

  public PlaceOrderRequest setRejectPostOnly(Boolean rejectPostOnly) {
    this.reject_post_only = rejectPostOnly;
    return this;
  }

  public PlaceOrderRequest setReduceOnly(Boolean reduceOnly) {
    this.reduce_only = reduceOnly;
    return this;
  }

  public PlaceOrderRequest setStopPrice(double stopPrice) {
    this.stop_price = stopPrice;
    return this;
  }

  public PlaceOrderRequest setTrigger(String trigger) {
    this.trigger = trigger;
    return this;
  }

  public PlaceOrderRequest setAdvanced(String advanced) {
    this.advanced = advanced;
    return this;
  }

  public PlaceOrderRequest setMmp(boolean mmp) {
    this.mmp = mmp;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected abstract String getEndpointPath();

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(instrument_name);
    builder.add("instrument_name", instrument_name);

    builder.add("amount", amount);

    requireNonNull(type);
    builder.add("type", type);

    if (label != null) {
      builder.add("label", label);
    }

    if (price != null) {
      builder.add("price", price);
    }

    if (time_in_force != null) {
      builder.add("time_in_force", time_in_force);
    }

    if (max_show != null) {
      builder.add("max_show", max_show);
    }

    if (post_only != null) {
      builder.add("postOnly", post_only);
    }

    if (reject_post_only != null) {
      builder.add("reject_post_only", reject_post_only);
    }

    if (reduce_only != null) {
      builder.add("reduceOnly", reduce_only);
    }

    if (stop_price != null) {
      builder.add("stop_price", stop_price);
    }

    if (trigger != null) {
      builder.add("trigger", trigger);
    }

    if (advanced != null) {
      builder.add("advanced", advanced);
    }

    if (mmp != null) {
      builder.add("mmp", mmp);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_MATCHING_ENGINE_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_PlaceOrderResponse> {
  }
}
