package io.contek.invoker.ftx.api.websocket.market;

import com.google.common.base.Joiner;
import io.contek.invoker.ftx.api.common._Trade;
import io.contek.invoker.ftx.api.websocket.WebSocketChannel;
import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._trades;

@ThreadSafe
public final class TradesChannel extends WebSocketChannel<TradesChannel.Message> {

  private final String market;

  TradesChannel(String market) {
    this.market = market;
  }

  @Override
  protected String getChannel() {
    return _trades;
  }

  @Override
  protected String getMarket() {
    return market;
  }

  @Override
  protected String getDisplayName() {
    return Joiner.on(':').join(_trades, market);
  }

  @Override
  protected Class<TradesChannel.Message> getMessageType() {
    return TradesChannel.Message.class;
  }

  @Override
  protected boolean accepts(TradesChannel.Message message) {
    return market.equals(message.market);
  }

  @NotThreadSafe
  public static final class Data extends ArrayList<_Trade> {}

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<TradesChannel.Data> {}
}
