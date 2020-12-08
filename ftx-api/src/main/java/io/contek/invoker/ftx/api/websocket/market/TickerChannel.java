package io.contek.invoker.ftx.api.websocket.market;

import com.google.common.base.Joiner;
import io.contek.invoker.ftx.api.common._Ticker;
import io.contek.invoker.ftx.api.websocket.WebSocketChannel;
import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._ticker;

@ThreadSafe
public final class TickerChannel extends WebSocketChannel<TickerChannel.Message> {

  private final String market;

  TickerChannel(String market) {
    this.market = market;
  }

  @Override
  protected String getChannel() {
    return _ticker;
  }

  @Override
  protected String getMarket() {
    return market;
  }

  @Override
  protected String getDisplayName() {
    return Joiner.on(':').join(_ticker, market);
  }

  @Override
  protected Class<TickerChannel.Message> getMessageType() {
    return TickerChannel.Message.class;
  }

  @Override
  protected boolean accepts(TickerChannel.Message message) {
    return market.equals(message.market);
  }

  @NotThreadSafe
  public static final class Data extends _Ticker {
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<TickerChannel.Data> {
  }
}
