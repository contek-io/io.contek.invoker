package io.contek.invoker.deribit.api.websocket.market;

import com.google.common.base.Joiner;
import io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys;
import io.contek.invoker.deribit.api.common._Ticker;
import io.contek.invoker.deribit.api.websocket.WebSocketChannel;
import io.contek.invoker.deribit.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class TickerChannel extends WebSocketChannel<TickerChannel.Message> {

  private final String market;

  TickerChannel(String market) {
    this.market = market;
  }

  @Override
  protected String getChannel() {
    return WebSocketChannelKeys._ticker;
  }

  @Override
  protected String getMarket() {
    return market;
  }

  @Override
  protected String getDisplayName() {
    return Joiner.on(':').join(WebSocketChannelKeys._ticker, market);
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
