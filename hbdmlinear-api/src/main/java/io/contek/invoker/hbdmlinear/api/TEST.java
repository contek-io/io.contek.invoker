package io.contek.invoker.hbdmlinear.api;

import io.contek.invoker.commons.websocket.ConsumerState;
import io.contek.invoker.commons.websocket.ISubscribingConsumer;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.hbdmlinear.api.websocket.user.LiquidationOrderChannel;

public class TEST implements ISubscribingConsumer<LiquidationOrderChannel.Message> {

  @Override
  public void onStateChange(SubscriptionState state) {}

  @Override
  public void onNext(LiquidationOrderChannel.Message message) {
    System.out.println(message.topic);
    System.out.println(message.data.size());
  }

  @Override
  public ConsumerState getState() {
    return ConsumerState.ACTIVE;
  }

  public static void main(String[] args) {
    LiquidationOrderChannel channel =
        ApiFactory.getMainNet()
            .ws()
            .user(null)
            .getLiquidationOrderChannel(LiquidationOrderChannel.Id.of("BTC-USDT"));
    channel.addConsumer(new TEST());
  }
}
