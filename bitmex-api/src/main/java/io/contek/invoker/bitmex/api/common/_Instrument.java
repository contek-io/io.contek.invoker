package io.contek.invoker.bitmex.api.common;

/** https://bitmexapi.com/#operation/Instrument.get */
public class _Instrument {

  /** Instrument symbol. */
  public String symbol;

  /**
   * The underlying asset symbol for the instrument. Can be used to group contracts by symbols like
   * BTC, ETH etc.
   */
  public String rootSymbol;

  /**
   * Current state of the instrument.
   *
   * <ul>
   *   <li>Open Open for trading
   *   <li>Settled Already settled, not available for trading.
   *   <li>Unlisted Never listed for trading. All indices are Unlisted.
   * </ul>
   */
  public String state;

  /**
   * Type of the instrument.
   *
   * <ul>
   *   <li>FFCCSX Futures
   *   <li>FFWCSX Perpetual contracts
   *   <li>OCECCS UPs
   *   <li>OPECCS DOWNs
   *   <li>MRCXXX Indices
   *   <li>FMXXS, FXXXS Deprecated
   * </ul>
   */
  public String typ;

  /** Date when the instrument get listed. */
  public String listing;

  /** Date when the instrument get expired. */
  public String expiry;

  /** Date when the instrument get settled, always same as expiry. */
  public String settle;

  /** Relist interval, only applys to UPs and DOWNs, that are settled and relisted every 7 days. */
  public String relistInterval;

  /** UPs and DOWNs only. Strike percent. */
  public Double optionStrikePcnt;

  /** UPs and DOWNs only. Strike round, always 250. */
  public Double optionStrikeRound;

  /** UPs and DOWNs only. Strike price. */
  public Double optionStrikePrice;

  /** UPs and DOWNs only. Contract multiplier. */
  public Double optionMultiplier;

  /**
   * Currency for your position of this contract. Depending on the type of this contract, It may
   * equal to underlying or quoteCurrency. Always an empty string for indices.
   */
  public String positionCurrency;

  /**
   * Key property that defines the underlying asset of the instrument, for both contracts and
   * indices.
   */
  public String underlying;

  /** Quote currency of this instrument. For some indices like .ETHUSDPI, this would be XXX. */
  public String quoteCurrency;

  /** Underlying Symbol. Just another format of underlying. */
  public String underlyingSymbol;

  /**
   *
   *
   * <ul>
   *   <li>BTMX BitMEX Index
   *   <li>BMI Composite Index
   * </ul>
   */
  public String reference;

  /** Reference index symbol */
  public String referenceSymbol;

  /** Calculation interval. Only available for BTMX reference indices. */
  public String calcInterval;

  /** Publish interval. Available for all indices. */
  public String publishInterval;

  /** Publish time. Only available for BTMX reference indices. */
  public String publishTime;

  /**
   * Maximum order quantity. Currenty it's always 100000000 when quoteCurrency is XBT, and always
   * 10000000 when quoteCurrency is USD. Applys to contracts only.
   */
  public Double maxOrderQty;

  /** Maximum price. Applys to contracts only. */
  public Double maxPrice;

  /**
   * Lot size. The minimum unit in io.contek.invoker.ftx.api.a order quantity. Applys to contracts
   * only.
   */
  public Double lotSize;

  /** Tick size. The minimum Double step for the instrument. */
  public Double tickSize;

  /**
   * Multiplier. Applys to contracts only. Used in PnL calculation. Basicly means how much is one
   * contract worth. Always in XBt(Satoshi). For negative values, please refer to the official blog
   * https://blog.bitmex.com/bitmex-vs-cme-futures-guide/
   */
  public Double multiplier;

  /** Currency in Settlement. It's always XBt. */
  public String settlCurrency;

  /** Is quanto contract or not. Only ETHUSD is quanto. */
  public Boolean isQuanto;

  /** Is inverse contract or not. Only XBT contracts are inverse. */
  public Boolean isInverse;

  /** Initial margin rate. Taker fee not included. */
  public Double initMargin;

  /** Maintenance margin rate. Exit taker fee not included. */
  public Double maintMargin;

  /** Risk limit. */
  public Long riskLimit;

  /** Risk step. */
  public Long riskStep;

  /** Maker fee. */
  public Double makerFee;

  /** Taker fee. */
  public Double takerFee;

  /** Settlement fee. */
  public Double settlementFee;

  /** Only applys to quanto contracts. */
  public String fundingBaseSymbol;

  /** Only applys to quanto contracts. */
  public String fundingQuoteSymbol;

  /** Only applys to quanto contracts. */
  public String fundingPremiumSymbol;

  /** Only applys to quanto contracts. */
  public String fundingTimestamp;

  /** Only applys to quanto contracts. */
  public String fundingInterval;

  /** Only applys to quanto contracts. */
  public Double fundingRate;

  /** Only applys to quanto contracts. */
  public Double indicativeFundingRate;

  /** Opening timestamp of the last trading session of this contract. */
  public String openingTimestamp;

  /** Closing timestamp of the last trading session of this contract. */
  public String closingTimestamp;

  /** Session interval of this contract. */
  public String sessionInterval;

  /** Close Double of the previous trading session. */
  public Double prevClosePrice;

  /** Down limit of the order price. */
  public Double limitDownPrice;

  /** Up limit of the order price. */
  public Double limitUpPrice;

  /** Previous total volume at the end of the previous trading session. */
  public Double prevTotalVolume;

  /** Current total volume. */
  public Double totalVolume;

  /** Volume of the past 24 hours. */
  public Double volume24h;

  /** Previous total Double at the end of the previous trading session. */
  public Double prevTotalTurnover;

  /** Current total turnover. */
  public Double totalTurnover;

  /** Double of the current trading session. */
  public Double turnover;

  /** Double of the past 24 hours. */
  public Double turnover24h;

  /** The volume24h in home notional, which usually is base currency. Eg, XBT for XBTUSD. */
  public Double homeNotional24h;

  /** The volume24h in foreign notional, which usually is quote currency. Eg, USD for XBTUSD. */
  public Double foreignNotional24h;

  /** VWAP, Short for Volume Weighted Average Price, of the current trading session. */
  public Double vwap;

  /** Highest Double in the past 24 hours. */
  public Double highPrice;

  /** Lowest Double in the past 24 hours. */
  public Double lowPrice;

  /** Last price. */
  public Double lastPrice;

  /** Refer to https://www.bitmex.com/app/fairPriceMarking#Last-Price-Protected-Marking */
  public Double lastPriceProtected;

  /**
   * The relationship between the last trade's Double and the previous ones.
   *
   * <ul>
   *   <li>MinusTick this trade's Double is lower than the previous one.
   *   <li>ZeroMinusTick this trade's Double is equal to previous one, but lower than the last trade
   *       of io.contek.invoker.ftx.api.a different price.
   *   <li>ZeroPlusTick this trade's Double is equal to previous one, but higher than the last trade
   *       of io.contek.invoker.ftx.api.a different price.
   *   <li>PlusTick this trade's Double is higher than the previous one.
   * </ul>
   */
  public String lastTickDirection;

  /** Change Percentage in the past 24 hours. */
  public Double lastChangePcnt;

  /** Last bid price. */
  public Double bidPrice;

  /** Average Double of bidPrice and askPrice. */
  public Double midPrice;

  /** Last ask price. */
  public Double askPrice;

  /** Refer to https://www.bitmex.com/app/fairPriceMarking#Impact-Bid-Ask-and-Mid-Price */
  public Double impactBidPrice;

  /** Refer to https://www.bitmex.com/app/fairPriceMarking#Impact-Bid-Ask-and-Mid-Price */
  public Double impactMidPrice;

  /** Refer to https://www.bitmex.com/app/fairPriceMarking#Impact-Bid-Ask-and-Mid-Price */
  public Double impactAskPrice;

  /** The instrument has liquidity or not. */
  public Boolean hasLiquidity;

  /** Total number of contracts in existance of this instrument. */
  public Double openInterest;

  /** The openInterest in XBt(Satoshi). */
  public Long openValue;

  /**
   * Fair basis calculation method of this instrument. Refer to
   * https://www.bitmex.com/app/fairPriceMarking .
   *
   * <ul>
   *   <li>FundingRate for Perpetual
   *   <li>ImpactMidPrice for Futures
   *   <li>Empty string for indices and options
   * </ul>
   */
  public String fairMethod;

  /** Fair basis rate. */
  public Double fairBasisRate;

  /** Fair basis. */
  public Double fairBasis;

  /** Fair price. */
  public Double fairPrice;

  /** Mark method. */
  public String markMethod;

  /** Mark price. */
  public Double markPrice;

  /** Indicative tax rate. Always 0 for contracts, null for indices. */
  public Double indicativeTaxRate;

  /** Indicative settle price. */
  public Double indicativeSettlePrice;

  /** Option underlying price. null for others. */
  public Double optionUnderlyingPrice;

  /** Settled price, for settled contracts. null for others. */
  public Double settledPrice;

  /** Timestamp. */
  public String timestamp;

  @Override
  public String toString() {
    return "_Instrument{" +
            "symbol='" + symbol + '\'' +
            ", rootSymbol='" + rootSymbol + '\'' +
            ", state='" + state + '\'' +
            ", typ='" + typ + '\'' +
            ", listing='" + listing + '\'' +
            ", expiry='" + expiry + '\'' +
            ", settle='" + settle + '\'' +
            ", relistInterval='" + relistInterval + '\'' +
            ", optionStrikePcnt=" + optionStrikePcnt +
            ", optionStrikeRound=" + optionStrikeRound +
            ", optionStrikePrice=" + optionStrikePrice +
            ", optionMultiplier=" + optionMultiplier +
            ", positionCurrency='" + positionCurrency + '\'' +
            ", underlying='" + underlying + '\'' +
            ", quoteCurrency='" + quoteCurrency + '\'' +
            ", underlyingSymbol='" + underlyingSymbol + '\'' +
            ", reference='" + reference + '\'' +
            ", referenceSymbol='" + referenceSymbol + '\'' +
            ", calcInterval='" + calcInterval + '\'' +
            ", publishInterval='" + publishInterval + '\'' +
            ", publishTime='" + publishTime + '\'' +
            ", maxOrderQty=" + maxOrderQty +
            ", maxPrice=" + maxPrice +
            ", lotSize=" + lotSize +
            ", tickSize=" + tickSize +
            ", multiplier=" + multiplier +
            ", settlCurrency='" + settlCurrency + '\'' +
            ", isQuanto=" + isQuanto +
            ", isInverse=" + isInverse +
            ", initMargin=" + initMargin +
            ", maintMargin=" + maintMargin +
            ", riskLimit=" + riskLimit +
            ", riskStep=" + riskStep +
            ", makerFee=" + makerFee +
            ", takerFee=" + takerFee +
            ", settlementFee=" + settlementFee +
            ", fundingBaseSymbol='" + fundingBaseSymbol + '\'' +
            ", fundingQuoteSymbol='" + fundingQuoteSymbol + '\'' +
            ", fundingPremiumSymbol='" + fundingPremiumSymbol + '\'' +
            ", fundingTimestamp='" + fundingTimestamp + '\'' +
            ", fundingInterval='" + fundingInterval + '\'' +
            ", fundingRate=" + fundingRate +
            ", indicativeFundingRate=" + indicativeFundingRate +
            ", openingTimestamp='" + openingTimestamp + '\'' +
            ", closingTimestamp='" + closingTimestamp + '\'' +
            ", sessionInterval='" + sessionInterval + '\'' +
            ", prevClosePrice=" + prevClosePrice +
            ", limitDownPrice=" + limitDownPrice +
            ", limitUpPrice=" + limitUpPrice +
            ", prevTotalVolume=" + prevTotalVolume +
            ", totalVolume=" + totalVolume +
            ", volume24h=" + volume24h +
            ", prevTotalTurnover=" + prevTotalTurnover +
            ", totalTurnover=" + totalTurnover +
            ", turnover=" + turnover +
            ", turnover24h=" + turnover24h +
            ", homeNotional24h=" + homeNotional24h +
            ", foreignNotional24h=" + foreignNotional24h +
            ", vwap=" + vwap +
            ", highPrice=" + highPrice +
            ", lowPrice=" + lowPrice +
            ", lastPrice=" + lastPrice +
            ", lastPriceProtected=" + lastPriceProtected +
            ", lastTickDirection='" + lastTickDirection + '\'' +
            ", lastChangePcnt=" + lastChangePcnt +
            ", bidPrice=" + bidPrice +
            ", midPrice=" + midPrice +
            ", askPrice=" + askPrice +
            ", impactBidPrice=" + impactBidPrice +
            ", impactMidPrice=" + impactMidPrice +
            ", impactAskPrice=" + impactAskPrice +
            ", hasLiquidity=" + hasLiquidity +
            ", openInterest=" + openInterest +
            ", openValue=" + openValue +
            ", fairMethod='" + fairMethod + '\'' +
            ", fairBasisRate=" + fairBasisRate +
            ", fairBasis=" + fairBasis +
            ", fairPrice=" + fairPrice +
            ", markMethod='" + markMethod + '\'' +
            ", markPrice=" + markPrice +
            ", indicativeTaxRate=" + indicativeTaxRate +
            ", indicativeSettlePrice=" + indicativeSettlePrice +
            ", optionUnderlyingPrice=" + optionUnderlyingPrice +
            ", settledPrice=" + settledPrice +
            ", timestamp='" + timestamp + '\'' +
            '}';
  }
}
