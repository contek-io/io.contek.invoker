package io.contek.invoker.ftx.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serializable;
import java.util.List;

@NotThreadSafe
public class _OrderBook implements Serializable {

    public List<_OrderBookLevel> asks;
    public List<_OrderBookLevel> bids;

    @Override
    public String toString() {
        return "_OrderBook{" +
                "asks=" + asks +
                ", bids=" + bids +
                '}';
    }
}
