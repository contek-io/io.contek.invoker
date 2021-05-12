package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.math.BigDecimal;
import java.util.List;

import static io.contek.invoker.binancefutures.api.websocket.user.constants.UserEventTypeKeys._ACCOUNT_UPDATE;

@ThreadSafe
public final class AccountUpdateChannel
    extends UserWebSocketChannel<AccountUpdateChannel.Id, AccountUpdateChannel.Message> {

  AccountUpdateChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<AccountUpdateChannel.Message> getMessageType() {
    return AccountUpdateChannel.Message.class;
  }

  @Immutable
  public static final class Id extends UserWebSocketChannelId<AccountUpdateChannel.Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super(_ACCOUNT_UPDATE);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketEventMessage {

    public Long T; // transaction
    public UpdateData a; // account update

    @NotThreadSafe
    public static final class UpdateData {

      public String m; // event reason type
      public List<BalanceUpdate> B; // balance updates
      public List<PositionUpdate> P; // position updates
    }
  }

  @NotThreadSafe
  public static final class BalanceUpdate {

    public String a; // asset
    public BigDecimal wb; // wallet balance
    public BigDecimal cw; // cross wallet balance
  }

  @NotThreadSafe
  public static final class PositionUpdate {

    public String s; // symbol
    public BigDecimal pa; // position amount
    public BigDecimal ep; // entry price
    public BigDecimal cr; // pre-fee accumulated realized
    public BigDecimal up; // unrealized PnL
    public String mt; // margin type
    public BigDecimal iw; // isolated wallet
    public String ps; // position side
  }
}
