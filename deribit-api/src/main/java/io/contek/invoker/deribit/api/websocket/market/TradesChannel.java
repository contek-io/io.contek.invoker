package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.deribit.api.websocket.WebSocketChannel;
import io.contek.invoker.deribit.api.websocket.common.Params;
import io.contek.invoker.deribit.api.websocket.common.Trade;
import io.contek.invoker.deribit.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys._trades;

@ThreadSafe
public final class TradesChannel extends WebSocketChannel<TradesChannel.Message> {

  private final String fullChannelName;

  TradesChannel(String instrumentName) {
    this.fullChannelName = String.format("%s.%s.%s", _trades, instrumentName, "100ms");
  }

  @Override
  protected String getChannel() {
    return fullChannelName;
  }

  @Override
  protected String getDisplayName() {
    return fullChannelName;
  }

  @Override
  protected Class<TradesChannel.Message> getMessageType() {
    return TradesChannel.Message.class;
  }

  @Override
  protected boolean accepts(TradesChannel.Message message) {
    return fullChannelName.equals(message.params.channel);
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<Params<List<Trade>>> {
  }
}
