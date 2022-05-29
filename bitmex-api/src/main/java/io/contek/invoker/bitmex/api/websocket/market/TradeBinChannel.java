package io.contek.invoker.bitmex.api.websocket.market;

import io.contek.invoker.bitmex.api.common._TradeBin;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannel;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannelId;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;

import static java.lang.String.format;

public final class TradeBinChannel
    extends WebSocketChannel<TradeBinChannel.Id, TradeBinChannel.Message> {

  TradeBinChannel(TradeBinChannel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

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

  public static final class Message extends WebSocketTableDataMessage<_TradeBin> {}
}
