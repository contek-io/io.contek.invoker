package io.contek.invoker.bybitlinear.api.websocket.market;

import io.contek.invoker.bybitlinear.api.websocket.WebSocketChannel;
import io.contek.invoker.bybitlinear.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybitlinear.api.websocket.common.WebSocketTopicMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

@ThreadSafe
public final class InstrumentInfoChannel
    extends WebSocketChannel<InstrumentInfoChannel.Id, InstrumentInfoChannel.Message> {

  InstrumentInfoChannel(InstrumentInfoChannel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<Message> {

    private Id(String topic) {
      super(topic);
    }

    public static Id of(String symbol) {
      return new Id(String.format("instrument_info.100ms.%s", symbol));
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTopicMessage {

    public String type;
    public InstrumentInfoData data;
    public Long cross_seq;
    public Long timestamp_e6;
  }

  @NotThreadSafe
  public static final class InstrumentInfoData {

    public List<InstrumentInfo> delete;
    public List<InstrumentInfo> update;
    public List<InstrumentInfo> insert;
  }

  @NotThreadSafe
  public static final class InstrumentInfo {

    public String symbol; // Symbol
    public Long last_price_e4; // Latest transaction price 10^4
    public String last_tick_direction; // Tick Direction
    public Long prev_price_24h_e4; // Price of 24 hours ago * 10^4
    public Long price_24h_pcnt_e6; // Percentage change of market price relative to 24h * 10^4
    public Long high_price_24h_e4; // The highest price in the last 24 hours * 10^4
    public Long low_price_24h_e4; // Lowest price in the last 24 hours * 10^4
    public Long prev_price_1h_e4; // Hourly market price an hour ago * 10^4
    public Long price_1h_pcnt_e6; // Percentage change of market price relative to 1 hour ago * 10^6
    public Long mark_price_e4; // Mark price * 10^4
    public Long index_price_e4; // Index_price * 10^4
    public Long
        open_interest; // Open interest. The update is not immediate - slowest update is 1 minute
    public Long
        open_value_e8; // Open position value * 10^8. The update is not immediate - slowest update
    // is 1 minute
    public Long total_turnover_e8; // Total turnover * 10^8
    public Long turnover_24h_e8; // Turnover for 24H * 10^8
    public Long total_volume; // Total volume
    public Long volume_24h; // Trading volume in the last 24 hours
    public Long predicted_funding_rate_e6; // Predicted funding rate * 10^6
    public Long cross_seq; // Cross sequence (internal value)
    public String created_at; // Creation time
    public String updated_at; // Update time
    public String next_funding_time; // Next settlement time of capital cost
    public Long countdown_hour; // Countdown of settlement capital cost
  }
}
