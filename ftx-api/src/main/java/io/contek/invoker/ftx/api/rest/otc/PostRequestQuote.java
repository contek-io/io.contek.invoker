package io.contek.invoker.ftx.api.rest.otc;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Quote;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Objects;

import static io.contek.invoker.commons.rest.RestMethod.POST;

@NotThreadSafe
public class PostRequestQuote extends OTCQuotesRestRequest<PostRequestQuote.Response> {

    private String baseCurrency;
    private String quoteCurrency;
    private String side;
    private Double baseCurrencySize;
    private Double quoteCurrencySize;
    private int secondsUntilSettlement;
    private boolean counterpartyAutoSettles;
    private boolean waitForPrice;

    public PostRequestQuote(IActor actor, RestContext context) {
        super(actor, context);
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public PostRequestQuote setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
        return this;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public PostRequestQuote setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
        return this;
    }

    public String getSide() {
        return side;
    }

    public PostRequestQuote setSide(String side) {
        this.side = side;
        return this;
    }

    public Double getBaseCurrencySize() {
        return baseCurrencySize;
    }

    public PostRequestQuote setBaseCurrencySize(Double baseCurrencySize) {
        this.baseCurrencySize = baseCurrencySize;
        return this;
    }

    public Double getQuoteCurrencySize() {
        return quoteCurrencySize;
    }

    public PostRequestQuote setQuoteCurrencySize(Double quoteCurrencySize) {
        this.quoteCurrencySize = quoteCurrencySize;
        return this;
    }

    public int getSecondsUntilSettlement() {
        return secondsUntilSettlement;
    }

    public PostRequestQuote setSecondsUntilSettlement(int secondsUntilSettlement) {
        this.secondsUntilSettlement = secondsUntilSettlement;
        return this;
    }

    public boolean isCounterpartyAutoSettles() {
        return counterpartyAutoSettles;
    }

    public PostRequestQuote setCounterpartyAutoSettles(boolean counterpartyAutoSettles) {
        this.counterpartyAutoSettles = counterpartyAutoSettles;
        return this;
    }

    public boolean isWaitForPrice() {
        return waitForPrice;
    }

    public PostRequestQuote setWaitForPrice(boolean waitForPrice) {
        this.waitForPrice = waitForPrice;
        return this;
    }

    @Override
    protected RestMethod getMethod() {
        return POST;
    }

    @Override
    protected String getEndpointPathOTC() {
        return "";
    }

    @Override
    protected RestParams getParams() {
        Objects.requireNonNull(baseCurrency);
        Objects.requireNonNull(quoteCurrency);
        Objects.requireNonNull(side);
        Objects.requireNonNull(baseCurrencySize);
        Objects.requireNonNull(quoteCurrency);
        return RestParams.newBuilder()
                .add("apiOnly", false)
                .add("baseCurrency", baseCurrency)
                .add("quoteCurrency", quoteCurrency)
                .add("side", side)
                .add("baseCurrencySize", baseCurrencySize)
                .add("quoteCurrency", quoteCurrency)
                .add("counterpartyAutoSettles", counterpartyAutoSettles)
                .add("waitForPrice", waitForPrice)
                .build();
    }

    @Override
    protected Class<Response> getResponseType() {
        return Response.class;
    }

    @NotThreadSafe
    public static final class Response extends RestResponse<_Quote> {
    }
}
