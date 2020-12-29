package io.contek.invoker.security;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface ICredentialFactory {

  ICredential create(ApiKey apiKey);
}
