package io.contek.invoker.security;

public interface ICredentialFactory {

  ICredential create(ApiKey apiKey);
}
