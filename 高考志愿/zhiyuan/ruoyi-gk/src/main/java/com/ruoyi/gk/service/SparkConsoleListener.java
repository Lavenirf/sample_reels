package com.ruoyi.gk.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.briqt.spark4j.listener.SparkBaseListener;
import io.github.briqt.spark4j.model.SparkMessage;
import io.github.briqt.spark4j.model.request.SparkRequest;
import io.github.briqt.spark4j.model.response.SparkResponse;
import io.github.briqt.spark4j.model.response.SparkResponseUsage;
import io.github.briqt.spark4j.model.response.SparkTextUsage;
import okhttp3.WebSocket;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SparkConsoleListener extends SparkBaseListener {

    private final StringBuilder stringBuilder = new StringBuilder();
    private final AtomicReference<String> lastMessage = new AtomicReference<>("");
    private MessageCallback callback;

    public ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public interface MessageCallback {
        void onNewMessage(String message);
    }

    public void setMessageCallback(MessageCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onMessage(String content, SparkResponseUsage usage, Integer status, SparkRequest sparkRequest, SparkResponse sparkResponse, WebSocket webSocket) {
        stringBuilder.append(content);

        if (0 == status) {
            List<SparkMessage> messages = sparkRequest.getPayload().getMessage().getText();
            try {
                System.out.println("提问：" + objectMapper.writeValueAsString(messages));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            System.out.println("\n收到回答：\n");
        }

        try {
            System.out.println("--content：" + content + " --完整响应：" + objectMapper.writeValueAsString(sparkResponse));
            callback.onNewMessage(objectMapper.writeValueAsString(sparkResponse));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if (2 == status) {
            System.out.println("\n完整回答：" + stringBuilder.toString());
            SparkTextUsage textUsage = usage.getText();
            System.out.println("\n回答结束；提问tokens：" + textUsage.getPromptTokens()
                    + "，回答tokens：" + textUsage.getCompletionTokens()
                    + "，总消耗tokens：" + textUsage.getTotalTokens());
            lastMessage.set(stringBuilder.toString());  // 保存最新的完整回答

            if (callback != null) {
                callback.onNewMessage(stringBuilder.toString());
            }
        }
    }

    public String getLastMessage() {
        return lastMessage.get();
    }

    public void resetLastMessage() {
        lastMessage.set("");
    }
}
