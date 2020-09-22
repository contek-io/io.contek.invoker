package io.contek.invoker.bitmex.api.websocket.market;

import io.contek.invoker.bitmex.api.common._TradeBin;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannel;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;
import io.contek.invoker.bitmex.api.websocket.market.TradeBinChannel.Message;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static java.text.MessageFormat.format;

@ThreadSafe
public final class TradeBinChannel extends WebSocketChannel<Message> {

  private final String binSize;
  private final String instrument;

  TradeBinChannel(String binSize, String instrument) {
    this.binSize = binSize;
    this.instrument = instrument;
  }

  @Override
  protected String getTopic() {
    return format("tradeBin{0}:{1}", binSize, instrument);
  }

  @Override
  protected Class<Message> getMessageType() {
    return Message.class;
  }

  @Override
  protected boolean accepts(Message message) {
    return message.data.stream().map(tradeBin -> tradeBin.symbol).anyMatch(instrument::equals);
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTableDataMessage<_TradeBin> {}
}
