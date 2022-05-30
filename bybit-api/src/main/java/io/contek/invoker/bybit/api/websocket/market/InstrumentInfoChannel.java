package io.contek.invoker.bybit.api.websocket.market;

import io.contek.invoker.bybit.api.websocket.WebSocketChannel;
import io.contek.invoker.bybit.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;

import java.util.List;

public final class InstrumentInfoChannel
    extends WebSocketChannel<InstrumentInfoChannel.Id, InstrumentInfoChannel.Message> {

  InstrumentInfoChannel(InstrumentInfoChannel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  public static final class Id extends WebSocketChannelId<InstrumentInfoChannel.Message> {

    private Id(String topic) {
      super(topic);
    }

    public static Id of(String symbol) {
      return new Id(String.format("instrument_info.100ms.%s", symbol));
    }
  }

  public static final class Message extends WebSocketTopicMessage {

    public String type;
    public InstrumentInfoData data;
    public Long cross_seq;
    public Long timestamp_e6;

    @Override
    public String toString() {
      return "Message{" +
              "type='" + type + '\'' +
              ", data=" + data +
              ", cross_seq=" + cross_seq +
              ", timestamp_e6=" + timestamp_e6 +
              '}';
    }
  }

  public static final class InstrumentInfoData {

    public List<InstrumentInfo> delete;
    public List<InstrumentInfo> update;
    public List<InstrumentInfo> insert;

    @Override
    public String toString() {
      return "InstrumentInfoData{" +
              "delete=" + delete +
              ", update=" + update +
              ", insert=" + insert +
              '}';
    }
  }

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

    @Override
    public String toString() {
      return "InstrumentInfo{" +
              "symbol='" + symbol + '\'' +
              ", last_price_e4=" + last_price_e4 +
              ", last_tick_direction='" + last_tick_direction + '\'' +
              ", prev_price_24h_e4=" + prev_price_24h_e4 +
              ", price_24h_pcnt_e6=" + price_24h_pcnt_e6 +
              ", high_price_24h_e4=" + high_price_24h_e4 +
              ", low_price_24h_e4=" + low_price_24h_e4 +
              ", prev_price_1h_e4=" + prev_price_1h_e4 +
              ", price_1h_pcnt_e6=" + price_1h_pcnt_e6 +
              ", mark_price_e4=" + mark_price_e4 +
              ", index_price_e4=" + index_price_e4 +
              ", open_interest=" + open_interest +
              ", open_value_e8=" + open_value_e8 +
              ", total_turnover_e8=" + total_turnover_e8 +
              ", turnover_24h_e8=" + turnover_24h_e8 +
              ", total_volume=" + total_volume +
              ", volume_24h=" + volume_24h +
              ", predicted_funding_rate_e6=" + predicted_funding_rate_e6 +
              ", cross_seq=" + cross_seq +
              ", created_at='" + created_at + '\'' +
              ", updated_at='" + updated_at + '\'' +
              ", next_funding_time='" + next_funding_time + '\'' +
              ", countdown_hour=" + countdown_hour +
              '}';
    }
  }
}
