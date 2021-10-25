package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.deribit.api.websocket.WebSocketChannelId;
import io.contek.invoker.deribit.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.deribit.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys._ticker;
import static java.lang.String.format;

@ThreadSafe
public final class TickerChannel
    extends MarketWebSocketChannel<TickerChannel.Id, TickerChannel.Message> {

  TickerChannel(TickerChannel.Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<TickerChannel.Message> getMessageType() {
    return TickerChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<TickerChannel.Message> {

    private Id(String value) {
      super(value);
    }

    public static Id of(String instrumentName, String interval) {
      return new Id(format("%s.%s.%s", _ticker, instrumentName, interval));
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<Ticker> {}

  @NotThreadSafe
  public static final class Ticker {

    public Double ask_iv; // (Only for option) implied volatility for best ask
    public Double best_ask_amount; // It represents the requested order size of all best asks
    public Double best_ask_price; // The current best ask price, null if there aren't any asks
    public Double best_bid_amount; // It represents the requested order size of all best bids
    public Double best_bid_price; // The current best bid price, null if there aren't any bids
    public Double bid_iv; // (Only for option) implied volatility for best bid
    public Double current_funding; // Current funding (perpetual only)
    public Double delivery_price; // The settlement price for the instrument. Only when state = closed
    public Double funding_8h; // Funding 8h (perpetual only)
    public Greeks greeks; // Only for options
    public Double index_price; // Current index price
    public String instrument_name; // Unique instrument identifier
    public Double interest_rate; // Interest rate used in implied volatility calculations (options only)
    public Double last_price; // The price for the last trade
    public Double mark_iv; // (Only for option) implied volatility for mark price
    public Double mark_price; // The mark price for the instrument
    public Double max_price; // The maximum price for the future. Any buy orders you submit higher than this price, will be clamped to this maximum.
    public Double min_price; // The minimum price for the future. Any sell orders you submit lower than this price will be clamped to this minimum.
    public Double open_interest; // The total amount of outstanding contracts in the corresponding amount units. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
    public Double settlement_price; // The settlement price for the instrument. Only when state = open
    public String state; // The state of the order book. Possible values are open and closed.
    public Stats stats;
    public Long timestamp; // The timestamp (milliseconds since the Unix epoch)
    public Double underlying_index; // Name of the underlying future, or index_price (options only)
    public Double underlying_price; // Underlying price for implied volatility calculations (options only)
  }

  @NotThreadSafe
  public static final class Greeks {

    public Double delta; // (Only for option) The delta value for the option
    public Double gamma; // (Only for option) The gamma value for the option
    public Double rho; // (Only for option) The rho value for the option
    public Double theta; // (Only for option) The theta value for the option
    public Double vega; // (Only for option) The vega value for the option
  }

  @NotThreadSafe
  public static final class Stats {

    public Double high; // Highest price during 24h
    public Double low; // Lowest price during 24h
    public Double price_change; // 24-hour price change expressed as a percentage, null if there weren't any trades
    public Double volume; // Volume during last 24h in base currency
  }
}
