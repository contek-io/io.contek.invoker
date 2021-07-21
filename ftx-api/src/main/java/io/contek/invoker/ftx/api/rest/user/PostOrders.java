package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Order;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostOrders extends UserRestRequest<PostOrders.Response> {

    private String market;
    private String side;
    private Double price = null;
    private String type;
    private Double size;
    private Boolean reduceOnly;
    private Boolean ioc;
    private Boolean postOnly;
    private String clientId;

    public PostOrders(IActor actor, RestContext context) {
        super(actor, context);
    }

    public String getMarket() {
        return market;
    }

    public PostOrders setMarket(String market) {
        this.market = market;
        return this;
    }

    public String getSide() {
        return side;
    }

    public PostOrders setSide(String side) {
        this.side = side;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public PostOrders setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getType() {
        return type;
    }

    public PostOrders setType(String type) {
        this.type = type;
        return this;
    }

    public Double getSize() {
        return size;
    }

    public PostOrders setSize(Double size) {
        this.size = size;
        return this;
    }

    public Boolean getReduceOnly() {
        return reduceOnly;
    }

    public PostOrders setReduceOnly(Boolean reduceOnly) {
        this.reduceOnly = reduceOnly;
        return this;
    }

    public Boolean getIoc() {
        return ioc;
    }

    public PostOrders setIoc(Boolean ioc) {
        this.ioc = ioc;
        return this;
    }

    public Boolean getPostOnly() {
        return postOnly;
    }

    public PostOrders setPostOnly(Boolean postOnly) {
        this.postOnly = postOnly;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public PostOrders setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    @Override
    protected RestMethod getMethod() {
        return RestMethod.POST;
    }

    @Override
    protected String getEndpointPath() {
        return "/api/orders";
    }

    @Override
    protected RestParams getParams() {
        RestParams.Builder builder = RestParams.newBuilder();

        requireNonNull(market);
        builder.add("market", market);

        requireNonNull(side);
        builder.add("side", side);

        requireNonNull(type);
        builder.add("type", type);

        requireNonNull(size);
        builder.add("size", size);

        builder.add("price", price);

        if (reduceOnly != null) {
            builder.add("reduceOnly", reduceOnly);
        }

        if (ioc != null) {
            builder.add("ioc", ioc);
        }

        if (postOnly != null) {
            builder.add("postOnly", postOnly);
        }

        if (clientId != null) {
            builder.add("clientId", clientId);
        }

        return builder.build();
    }

    @Override
    protected Class<Response> getResponseType() {
        return Response.class;
    }

    @NotThreadSafe
    public static final class Response extends RestResponse<_Order> {
    }
}
