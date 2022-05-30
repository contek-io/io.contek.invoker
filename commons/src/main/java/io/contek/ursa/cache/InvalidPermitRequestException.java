package io.contek.ursa.cache;

import io.contek.invoker.ursa.core.api.UrsaException;

public class InvalidPermitRequestException extends UrsaException {
  InvalidPermitRequestException(String field) {
    super(String.format("Missing rate limit %s", new Object[] {field}));
  }
}
