package io.contek.invoker.ftx.api.rest.market;

import io.contek.invoker.ftx.api.ApiFactory;
import org.junit.jupiter.api.Test;

import static io.contek.invoker.ftx.api.rest.Constants.SUCCESS_FIELD;
import static org.assertj.core.api.Assertions.assertThat;

class GetMarketsTest {
  @Test
  void testGetMarketsSuccess() {
    final MarketRestApi restApi = ApiFactory.getMainNet().rest().market();
    final GetMarkets.Response response = restApi.getMarkets().submit();
    assertThat(response).hasFieldOrPropertyWithValue(SUCCESS_FIELD, true);
    assertThat(response.result)
        .isNotEmpty()
        .element(0)
        .hasNoNullFieldsOrPropertiesExcept("baseCurrency", "quoteCurrency");
  }
}
