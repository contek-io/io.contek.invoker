package io.contek.invoker.ftx.api.websocket.common;

public class WebSocketAuthenticationMessage extends WebSocketOutboundMessage {
    public Args args;

    public static class Args {
        public String key;
        public String sign;
        public Long time;
        public String subaccount;
    }
}
