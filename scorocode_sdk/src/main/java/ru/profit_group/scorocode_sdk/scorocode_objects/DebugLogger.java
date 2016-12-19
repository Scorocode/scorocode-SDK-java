package ru.profit_group.scorocode_sdk.scorocode_objects;

/**
 * Created by Peter Staranchuk on 11/29/16
 */

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketCall;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

public class DebugLogger implements WebSocketListener {
    private WebSocket webSocket;
    private LoggerType loggerType;
    private String scriptId;
    private OnLoggerReadyCallback callback;
    private OnLoggerReceivedMessage onLoggerReceivedMessage;

    private DebugLogger(LoggerType loggerType, OnLoggerReadyCallback callback) {
        this.loggerType = loggerType;
        this.callback = callback;
    }

    private DebugLogger(String scriptId, OnLoggerReadyCallback callback) {
        this(LoggerType.SCRIPT, callback);
        this.scriptId = scriptId;
    }

    public static DebugLogger getScriptDebugLoggerInstance(String scriptId, OnLoggerReadyCallback onLoggerReady) {
        return new DebugLogger(scriptId, onLoggerReady);
    }

    public static DebugLogger getMessageDebugLoggerInstance(OnLoggerReadyCallback onLoggerReady) {
        return new DebugLogger(LoggerType.MESSAGE, onLoggerReady);
    }

    public void run() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .build();

        Request.Builder builder = new Request.Builder();

        switch (loggerType) {
            case MESSAGE:
                builder.url(ScorocodeSdk.getMessageDebugEndpoint());
                break;

            case SCRIPT:
                builder.url(ScorocodeSdk.getScriptDebugEndpoint(scriptId));
                break;
        }

        WebSocketCall webSocketCall = WebSocketCall.create(client, builder.build());
        webSocketCall.enqueue(this);

        // Trigger shutdown of the dispatcher's executor so this process can exit cleanly.
        client.dispatcher().executorService().shutdown();
    }

    public void stop() throws IOException {
        if(webSocket != null) {
            webSocket.close(0, "closed by user");
        }
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        this.webSocket = webSocket;
        if(callback != null) {
            callback.onLoggerReady();
        }
    }

    public void setOnMessageListener(OnLoggerReceivedMessage listener) {
        this.onLoggerReceivedMessage = listener;
    }

    @Override
    public void onFailure(IOException e, Response response) {
        if(onLoggerReceivedMessage != null) {
            try {
                onLoggerReceivedMessage.onMessage("Failure: "+ response.body().string() + " " + "ErrorMessage: " + e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void onMessage(ResponseBody message) throws IOException {
        if(onLoggerReceivedMessage != null) {
            onLoggerReceivedMessage.onMessage("Message: " + message.string());
        }
    }

    @Override
    public void onPong(Buffer payload) {
        if(onLoggerReceivedMessage != null) {
            onLoggerReceivedMessage.onMessage("onPong: " + payload.toString());
        }
    }

    @Override
    public void onClose(int code, String reason) {
        if(onLoggerReceivedMessage != null) {
            onLoggerReceivedMessage.onMessage("onClose:"+ "\n" + "Code: " + code + "\n" + "Reason: " + reason);
        }
    }

    public enum LoggerType{
        MESSAGE, SCRIPT
    }

    public interface OnLoggerReadyCallback {
        void onLoggerReady();
    }

    public interface OnLoggerReceivedMessage {
        void onMessage(String message);
    }
}
