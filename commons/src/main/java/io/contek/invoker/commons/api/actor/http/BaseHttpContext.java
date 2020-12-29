package io.contek.invoker.commons.api.actor.http;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class BaseHttpContext implements IHttpContext {

  private final String baseUrl;

  protected BaseHttpContext(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  @Override
  public final String getBaseUrl() {
    return baseUrl;
  }
}
