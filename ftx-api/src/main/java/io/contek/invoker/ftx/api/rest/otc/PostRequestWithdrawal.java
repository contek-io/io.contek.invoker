package io.contek.invoker.ftx.api.rest.otc;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Withdrawal;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Objects;

@NotThreadSafe
public class PostRequestWithdrawal extends OTCWithdrawalsRestRequest<PostRequestWithdrawal.Response> {

    private String coin;
    private String address;
    private String tag;
    private Double size;
    private Boolean fastWithdrawal;
    private String password;
    private String code;

    public PostRequestWithdrawal(IActor actor, RestContext context) {
        super(actor, context);
    }

    public String getCoin() {
        return coin;
    }

    public PostRequestWithdrawal setCoin(String coin) {
        this.coin = coin;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public PostRequestWithdrawal setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public PostRequestWithdrawal setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public Double getSize() {
        return size;
    }

    public PostRequestWithdrawal setSize(Double size) {
        this.size = size;
        return this;
    }

    public Boolean getFastWithdrawal() {
        return fastWithdrawal;
    }

    public PostRequestWithdrawal setFastWithdrawal(Boolean fastWithdrawal) {
        this.fastWithdrawal = fastWithdrawal;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public PostRequestWithdrawal setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCode() {
        return code;
    }

    public PostRequestWithdrawal setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    protected RestMethod getMethod() {
        return RestMethod.POST;
    }

    @Override
    protected String getEndpointPathOTC() {
        return "";
    }

    @Override
    protected RestParams getParams() {
        Objects.requireNonNull(coin);
        Objects.requireNonNull(address);
        Objects.requireNonNull(size);
        RestParams.Builder builder = RestParams.newBuilder()
                .add("coin", coin)
                .add("address", address)
                .add("size", size);

        if (tag != null) {
            builder.add("tag", tag);
        }

        if (fastWithdrawal != null) {
            builder.add("fastWithdrawal", fastWithdrawal);
        }

        if (password != null) {
            builder.add("password", password);
        }

        if (code != null) {
            builder.add("code", code);
        }
        return builder.build();
    }

    @Override
    protected Class<Response> getResponseType() {
        return Response.class;
    }

    @NotThreadSafe
    public static final class Response extends RestResponse<_Withdrawal> {
    }
}
