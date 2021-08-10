package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.ftx.api.ApiFactory;
import io.contek.invoker.security.ApiKey;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GetOrderHistoryTest {

  private static final String SUCCESS_FIELD = "success";
  private static final String FTX_API_KEY_ENV_VARIABLE = "FTX_API_KEY";
  private static final String FTX_API_SECRET_ENV_VARIABLE = "FTX_API_SECRET";

  @Test
  void testOrderHistorySuccess() {
    final UserRestApi restApi =
        ApiFactory.getMainNet()
            .rest()
            .user(
                ApiKey.newBuilder()
                    .setId(System.getenv(FTX_API_KEY_ENV_VARIABLE))
                    .setSecret(System.getenv(FTX_API_SECRET_ENV_VARIABLE))
                    .build());
    final GetOrderHistory.Response response = restApi.getOrderHistory().submit();
    assertThat(response).hasFieldOrPropertyWithValue(SUCCESS_FIELD, true);
  }
}
