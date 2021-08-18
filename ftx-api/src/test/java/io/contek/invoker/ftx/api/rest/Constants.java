package io.contek.invoker.ftx.api.rest;

import io.contek.invoker.ftx.api.ApiFactory;
import io.contek.invoker.ftx.api.rest.user.UserRestApi;
import io.contek.invoker.security.ApiKey;

public final class Constants {
  public static final String SUCCESS_FIELD = "success";
  public static final String FTX_API_KEY_ENV_VARIABLE = "FTX_API_KEY";
  public static final String FTX_API_SECRET_ENV_VARIABLE = "FTX_API_SECRET";

  public static final UserRestApi USER_REST_API =
      ApiFactory.getMainNet()
          .rest()
          .user(
              ApiKey.newBuilder()
                  .setId(System.getenv(FTX_API_KEY_ENV_VARIABLE))
                  .setSecret(System.getenv(FTX_API_SECRET_ENV_VARIABLE))
                  .build());
}
