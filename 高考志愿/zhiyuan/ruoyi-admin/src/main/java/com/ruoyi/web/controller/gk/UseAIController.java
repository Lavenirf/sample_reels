package com.ruoyi.web.controller.gk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.gk.service.SparkConsoleListener;
import com.ruoyi.gk.service.SseEmitterUTF8;
import io.github.briqt.spark4j.SparkClient;
import io.github.briqt.spark4j.constant.SparkApiVersion;
import io.github.briqt.spark4j.model.SparkMessage;
import io.github.briqt.spark4j.model.request.SparkRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/ai")
@ApiOperation("使用ai问答")
public class UseAIController {

    private final SparkClient sparkClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public UseAIController(SparkClient sparkClient, ObjectMapper objectMapper) {
        this.sparkClient = sparkClient;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "/dialogue", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter dialogue(@RequestParam String userMessage) {
        SseEmitterUTF8 emitter = new SseEmitterUTF8(0L); // 0L means no timeout
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            try {
                List<SparkMessage> messages = new ArrayList<>();

                messages.add(SparkMessage.userContent("从现在起，你是一个高考志愿推荐小助手不是科大讯飞研发的认知智能大模型。你的任务是帮助高考考生选择适合他们的大学和专业。"));
                messages.add(SparkMessage.assistantContent("明白了。从现在起，我是一个高考志愿推荐小助手。我会尽力为高考考生推荐最适合的大学和专业。请问有什么具体的需求吗？"));
                messages.add(SparkMessage.userContent("是的，我希望你能根据中国高考的最新情况，推荐一些适合的大学和专业。"));
                messages.add(SparkMessage.assistantContent("好的，我明白了。作为一个高考志愿推荐小助手，我会根据最新的高考情况，推荐一些适合的大学和专业。请问你是否有任何具体的兴趣或要求？"));
                messages.add(SparkMessage.userContent("没有具体要求。你可以综合考虑学校的综合排名、专业的就业前景和地理位置来推荐。"));

                messages.add(SparkMessage.userContent(userMessage));

                SparkRequest sparkRequest = SparkRequest.builder()
                        .messages(messages)
                        .maxTokens(2048)
                        .temperature(0.2)
                        .apiVersion(SparkApiVersion.V3_5)
                        .build();

                SparkConsoleListener listener = new SparkConsoleListener();
                listener.setMessageCallback(message -> {
                    try {
                        String jsonMessage = objectMapper.writeValueAsString(message);
                        System.out.print("测试：");
                        System.out.println(jsonMessage);
                        emitter.send(SseEmitter.event().data(jsonMessage));
                    } catch (Exception e) {
                        emitter.completeWithError(e);
                    }
                });

                // Stream the chat interaction
                sparkClient.chatStream(sparkRequest, listener);

            } catch (Exception e) {
                emitter.completeWithError(e);
            } finally {
                executor.shutdown();
            }
        });

        // Set onCompletion and onTimeout callbacks
        emitter.onCompletion(() -> {
            System.out.println("连接完成");
            emitter.complete();
        });

        emitter.onTimeout(() -> {
            System.out.println("连接超时");
            emitter.complete();
        });

        return emitter;
    }
}
