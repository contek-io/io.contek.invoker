package io.contek.invoker.deribit.api.websocket.market;

import com.google.common.base.Joiner;
import io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys;
import io.contek.invoker.deribit.api.common._Trade;
import io.contek.invoker.deribit.api.websocket.WebSocketChannel;
import io.contek.invoker.deribit.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;

@ThreadSafe
public final class TradesChannel extends WebSocketChannel<TradesChannel.Message> {

  private final String market;

  TradesChannel(String market) {
    this.market = market;
  }

  @Override
  protected String getChannel() {
    return WebSocketChannelKeys._trades;
  }

  @Override
  protected String getMarket() {
    return market;
  }

  @Override
  protected String getDisplayName() {
    return Joiner.on(':').join(WebSocketChannelKeys._trades, market);
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
