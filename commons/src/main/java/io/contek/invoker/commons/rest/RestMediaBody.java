package io.contek.invoker.commons.rest;

import com.google.common.net.MediaType;
import io.vertx.core.buffer.Buffer;

public record RestMediaBody(MediaType type, Buffer body) {

}
