package io.contek.invoker.bitmex.api.websocket.market;

import io.contek.invoker.bitmex.api.common._TradeBin;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannel;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannelId;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static java.lang.String.format;

@ThreadSafe
public final class TradeBinChannel extends WebSocketChannel<TradeBinChannel.Message, _TradeBin> {

  TradeBinChannel(TradeBinChannel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<TradeBinChannel.Message> {

    private final String binSize;
    private final String instrument;

    private Id(String binSize, String instrument) {
      super(format("tradeBin%s:%s", binSize, instrument));
      this.binSize = binSize;
      this.instrument = instrument;
    }

    public static Id of(String binSize, String instrument) {
      return new Id(binSize, instrument);
    }

    @Override
    public boolean accepts(TradeBinChannel.Message message) {
      return message.data.stream().map(tradeBin -> tradeBin.symbol).anyMatch(instrument::equals);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTableDataMessage<_TradeBin> {}
}
