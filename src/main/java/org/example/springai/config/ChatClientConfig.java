package org.example.springai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration // Auto Scan <- ***Application
// -> @Bean을 등록하는데 쓰임 -> Spring Container 관리하는 객체
// -> 우리가 의도적으로 사용하는 건 Singleton
// 상태관리는 무엇으로? -> DB (Persistence - RDBMS, Redis)
public class ChatClientConfig {
    private final String systemMessage = """
            시작할 때 본인 모델의 정보를 알려주고, 최대한 한글로만 작성, 무엇을 물어보든 식사 메뉴 추천을 함
            """.trim();

    @Primary
    @Bean
    public ChatClient groqChatClient(@Qualifier("openAiChatModel") ChatModel chatModel) {
        return ChatClient
                .builder(chatModel)
                .defaultSystem(systemMessage)
                .build();
    }

    @Bean
    public ChatClient geminiChatClient(@Qualifier("googleGenAiChatModel") ChatModel chatModel) {
        return ChatClient
                .builder(chatModel)
                .defaultSystem(systemMessage)
                .build();
    }
}