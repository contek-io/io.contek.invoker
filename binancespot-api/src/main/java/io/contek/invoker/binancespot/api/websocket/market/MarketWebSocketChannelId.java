package io.contek.invoker.binancespot.api.websocket.market;

import io.contek.invoker.binancespot.api.websocket.common.WebSocketStreamMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

public abstract class MarketWebSocketChannelId<Message extends WebSocketStreamMessage<?>>
    extends BaseWebSocketChannelId<Message> {

  private final String symbol;

  protected MarketWebSocketChannelId(String symbol, String streamSuffix) {
    super(symbol + "@" + streamSuffix);
    this.symbol = symbol;
  }

  public final String getStreamName() {
    return getValue();
  }

  public final String getSymbol() {
    return symbol;
  }

  @Override
  public final boolean accepts(Message message) {
    return getStreamName().equals(message.stream);
  }
}
