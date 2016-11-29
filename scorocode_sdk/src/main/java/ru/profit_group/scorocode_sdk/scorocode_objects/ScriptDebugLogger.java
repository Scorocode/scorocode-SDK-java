package ru.profit_group.scorocode_sdk.scorocode_objects;

/**
 * Created by Peter Staranchuk on 11/29/16
 */

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketCall;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

public class ScriptDebugLogger implements WebSocketListener {
    private Logger logger;
    private WebSocket webSocket;

    public ScriptDebugLogger() {
        logger = Logger.getLogger("MessageDebug");
    }

    public void run() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .build();

        Request request = new Request.Builder()
                .url(ScorocodeSdk.getMessageDebugEndpoint())
                .build();

        WebSocketCall webSocketCall = WebSocketCall.create(client, request);
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
    }

    @Override
    public void onFailure(IOException e, Response response) {
        logger.log(Level.ALL, "error: " + e.toString());
        try {
            logger.log(Level.ALL, "response: " + response.body().string());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onMessage(ResponseBody message) throws IOException {
        logger.log(Level.ALL, "message: " + message.string());
    }

    @Override
    public void onPong(Buffer payload) {
        logger.log(Level.ALL, "pong: " + payload.buffer().readUtf8());
    }

    @Override
    public void onClose(int code, String reason) {
        logger.log(Level.ALL, "code: " + code + " reason: " + reason);
    }
}
