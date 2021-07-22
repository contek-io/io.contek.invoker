package io.contek.invoker.binancefutures.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;

/**
 *
 *
 * <pre>
 *  0 openTime,
 *  1 open,
 *  2 high,
 *  3 low,
 *  4 close,
 *  5 volume,
 *  6 closeTime,
 *  7 quoteAssetVolume,
 *  8 numberOfTrades,
 *  9 takerBuyBaseAssetVolume,
 * 10 takerBuyQuoteAssetVolume
 * </pre>
 */
@NotThreadSafe
public class _Candlestick extends ArrayList<Double> {

    public double openTime() {
        return get(0);
    }

    public double open() {
        return get(1);
    }

    public double high() {
        return get(2);
    }

    public double low() {
        return get(3);
    }

    public double close() {
        return get(4);
    }

    public double volume() {
        return get(5);
    }

    public double closeTime() {
        return get(6);
    }

    public double baseAssetVolume() {
        return get(7);
    }

    public double numberOfTrades() {
        return get(8);
    }

    public double takerBuyBaseAssetVolume() {
        return get(9);
    }

    public double takerBuyQuoteAssetVolume() {
        return get(10);
    }
}
