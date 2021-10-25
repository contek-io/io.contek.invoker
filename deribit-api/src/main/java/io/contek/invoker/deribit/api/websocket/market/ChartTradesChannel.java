package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.deribit.api.websocket.WebSocketChannelId;
import io.contek.invoker.deribit.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.deribit.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys._chart;
import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys._trades;
import static java.lang.String.format;

@ThreadSafe
public final class ChartTradesChannel
    extends MarketWebSocketChannel<ChartTradesChannel.Id, ChartTradesChannel.Message> {

  ChartTradesChannel(ChartTradesChannel.Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<ChartTradesChannel.Message> getMessageType() {
    return ChartTradesChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<ChartTradesChannel.Message> {

    private Id(String value) {
      super(value);
    }

    public static Id of(String instrumentName, String resolution) {
      return new Id(format("%s.%s.%s.%s", _chart, _trades, resolution, instrumentName));
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<ChartTrade> {}

  @NotThreadSafe
  public static final class ChartTrade {

    public double close; // The close price for the candle
    public double cost; // Cost data for the candle
    public double high; // The highest price level for the candle
    public double low; // The lowest price level for the candle
    public double open; // The open price for the candle'
    public long tick; // The timestamp (milliseconds since the Unix epoch)
    public double volume; // Volume data for the candle
  }
}
