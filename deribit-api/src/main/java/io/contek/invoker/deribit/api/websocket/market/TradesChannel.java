package io.contek.invoker.deribit.api.websocket.market;

import com.google.common.base.Joiner;
import io.contek.invoker.deribit.api.websocket.WebSocketChannel;
import io.contek.invoker.deribit.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys._trades;

@ThreadSafe
public final class TradesChannel extends WebSocketChannel<TradesChannel.Message> {

  private final String instrumentName;

  TradesChannel(String instrumentName) {
    this.instrumentName = instrumentName;
  }

  @Override
  protected String getChannel() {
    return String.format("%s.%s.%s", _trades, instrumentName, "100ms");
  }

  @Override
  protected String getDisplayName() {
    return Joiner.on(':').join(WebSocketChannelKeys._trades, instrumentName);
  }

  @Override
  protected Class<TradesChannel.Message> getMessageType() {
    return TradesChannel.Message.class;
  }

  @Override
  protected boolean accepts(TradesChannel.Message message) {
    return instrumentName.equals(parseInstrumentName(message.params.channel));
  }

  private String parseInstrumentName(String name) {
    String[] parts = name.split("\\.");
    if (parts.length != 3) {
      return "";
    }
    return parts[1];
  }

  public static final class Trade {
    public long amount;
    public String block_trade_id;
    public String direction;
    public double index_price;
    public String instrument_name;
    public double iv;
    public String liquidation;
    public double mark_price;
    public double price;
    public int tick_direction;
    public long timestamp;
    public String trade_id;
    public long trade_seq;
  }


  public static final class Params {
    public String channel;
    public List<Trade> data;
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<TradesChannel.Params> {
  }
}
