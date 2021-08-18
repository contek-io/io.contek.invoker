package io.contek.invoker.ftx.api.rest.user;

import org.junit.jupiter.api.Test;

import static io.contek.invoker.ftx.api.rest.Constants.SUCCESS_FIELD;
import static io.contek.invoker.ftx.api.rest.Constants.USER_REST_API;
import static org.assertj.core.api.Assertions.assertThat;

class GetWalletBalancesTest {
  @Test
  void testGetWalletBalancesSuccess() {
    final GetWalletBalances.Response response = USER_REST_API.getWalletBalances().submit();
    assertThat(response).hasFieldOrPropertyWithValue(SUCCESS_FIELD, true);
    assertThat(response.result).isNotEmpty().element(0).hasNoNullFieldsOrProperties();
  }
}
