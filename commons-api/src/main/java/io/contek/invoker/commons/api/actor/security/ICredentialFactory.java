package io.contek.invoker.commons.api.actor.security;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface ICredentialFactory {

  ICredential create(ApiKey apiKey);
}
