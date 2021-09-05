package io.contek.invoker.ftx.api.rest.user;

import org.junit.jupiter.api.Test;

import static io.contek.invoker.ftx.api.rest.Constants.SUCCESS_FIELD;
import static io.contek.invoker.ftx.api.rest.Constants.USER_REST_API;
import static org.assertj.core.api.Assertions.assertThat;

class GetFillsTest {
  @Test
  void testGetFillsForOrderSuccess() {
    final GetFills.Response allFillsResponse = USER_REST_API.getFills().submit();
    assertThat(allFillsResponse).hasFieldOrPropertyWithValue(SUCCESS_FIELD, true);
    assertThat(allFillsResponse.result).isNotEmpty();

    final GetFills.Response fillsForOrderResponse =
        USER_REST_API.getFills().setOrderId(allFillsResponse.result.get(0).orderId).submit();
    assertThat(fillsForOrderResponse).hasFieldOrPropertyWithValue(SUCCESS_FIELD, true);
    assertThat(fillsForOrderResponse.result).isNotEmpty();
  }
}
