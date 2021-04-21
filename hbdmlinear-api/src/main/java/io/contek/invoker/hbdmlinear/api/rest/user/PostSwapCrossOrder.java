package io.contek.invoker.hbdmlinear.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.hbdmlinear.api.common._OrderIdentifier;
import io.contek.invoker.hbdmlinear.api.rest.common.RestDataResponse;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.hbdmlinear.api.ApiFactory.RateLimits.ONE_API_KEY_REST_PRIVATE_WRITE_REQUEST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostSwapCrossOrder extends UserRestRequest<PostSwapCrossOrder.Response> {

  private String contract_code;
  private Long client_order_id;
  private Double price;
  private Integer volume;
  private String direction;
  private String offset;
  private Integer lever_rate;
  private String order_price_type;
  private Double tp_trigger_price;
  private Double tp_order_price;
  private String tp_order_price_type;
  private Double sl_trigger_price;
  private Double sl_order_price;
  private String sl_order_price_type;

  PostSwapCrossOrder(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostSwapCrossOrder setContractCode(String contract_code) {
    this.contract_code = contract_code;
    return this;
  }

  public PostSwapCrossOrder setClientOrderId(@Nullable Long client_order_id) {
    this.client_order_id = client_order_id;
    return this;
  }

  public PostSwapCrossOrder setPrice(@Nullable Double price) {
    this.price = price;
    return this;
  }

  public PostSwapCrossOrder setVolume(int volume) {
    this.volume = volume;
    return this;
  }

  public PostSwapCrossOrder setDirection(String direction) {
    this.direction = direction;
    return this;
  }

  public PostSwapCrossOrder setOffset(String offset) {
    this.offset = offset;
    return this;
  }

  public PostSwapCrossOrder setLeverRate(Integer lever_rate) {
    this.lever_rate = lever_rate;
    return this;
  }

  public PostSwapCrossOrder setOrderPriceType(String order_price_type) {
    this.order_price_type = order_price_type;
    return this;
  }

  public PostSwapCrossOrder setTpTriggerPrice(@Nullable Double tp_trigger_price) {
    this.tp_trigger_price = tp_trigger_price;
    return this;
  }

  public PostSwapCrossOrder setTpOrderPrice(@Nullable Double tp_order_price) {
    this.tp_order_price = tp_order_price;
    return this;
  }

  public PostSwapCrossOrder setTpOrderPriceType(@Nullable String tp_order_price_type) {
    this.tp_order_price_type = tp_order_price_type;
    return this;
  }

  public PostSwapCrossOrder setSlTriggerPrice(@Nullable Double sl_trigger_price) {
    this.sl_trigger_price = sl_trigger_price;
    return this;
  }

  public PostSwapCrossOrder setSlOrderPrice(@Nullable Double sl_order_price) {
    this.sl_order_price = sl_order_price;
    return this;
  }

  public PostSwapCrossOrder setSlOrderPriceType(@Nullable String sl_order_price_type) {
    this.sl_order_price_type = sl_order_price_type;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/linear-swap-api/v1/swap_cross_order";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(contract_code);
    builder.add("contract_code", contract_code);

    requireNonNull(volume);
    builder.add("volume", volume);

    requireNonNull(direction);
    builder.add("direction", direction);

    requireNonNull(offset);
    builder.add("offset", offset);

    requireNonNull(lever_rate);
    builder.add("lever_rate", lever_rate);

    requireNonNull(order_price_type);
    builder.add("order_price_type", order_price_type);

    if (client_order_id != null) {
      builder.add("client_order_id", client_order_id);
    }

    if (price != null) {
      builder.add("price", price);
    }

    if (tp_trigger_price != null) {
      builder.add("tp_trigger_price", tp_trigger_price);
    }

    if (tp_order_price != null) {
      builder.add("tp_order_price", tp_order_price);
    }

    if (tp_order_price_type != null) {
      builder.add("tp_order_price_type", tp_order_price_type);
    }

    if (sl_trigger_price != null) {
      builder.add("sl_trigger_price", sl_trigger_price);
    }

    if (sl_order_price != null) {
      builder.add("sl_order_price", sl_order_price);
    }

    if (sl_order_price_type != null) {
      builder.add("sl_order_price_type", sl_order_price_type);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_API_KEY_REST_PRIVATE_WRITE_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestDataResponse<_OrderIdentifier> {}
}
