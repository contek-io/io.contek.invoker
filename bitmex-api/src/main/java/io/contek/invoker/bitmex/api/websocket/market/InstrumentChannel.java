package io.contek.invoker.bitmex.api.websocket.market;

import io.contek.invoker.bitmex.api.common._Instrument;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannel;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannelId;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;

import static java.lang.String.format;

public final class InstrumentChannel
    extends WebSocketChannel<InstrumentChannel.Id, InstrumentChannel.Message> {

  InstrumentChannel(InstrumentChannel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  public static final class Id extends WebSocketChannelId<InstrumentChannel.Message> {

    private final String instrument;

    private Id(String instrument) {
      super(format("instrument:%s", instrument));
      this.instrument = instrument;
    }

    public static Id of(String instrument) {
      return new Id(instrument);
    }

    @Override
    public boolean accepts(InstrumentChannel.Message message) {
      return message.data.stream().map(quote -> quote.symbol).anyMatch(instrument::equals);
    }
  }

  public static final class Message extends WebSocketTableDataMessage<_Instrument> {}
}
