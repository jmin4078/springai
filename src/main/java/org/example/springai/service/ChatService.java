package org.example.springai.service;

import lombok.RequiredArgsConstructor;
import org.example.springai.dto.ChatDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    // Could not autowire. There is more than one bean of 'ChatModel' type.
//    @Qualifier("openAiChatModel") // Bean 이름으로 타입 기반 의존성 주입된 것 중 필요한 걸 지정
//    private final ChatModel chatModel;
    private final ChatClient groqChatClient; // ChatClient로 현재는 등록된 게 1개

    //    @Qualifier("googleGenAiChatModel")
//    private final ChatModel geminiChatModel;
    @Qualifier("geminiChatClient")
    private final ChatClient geminiChatClient;

    public String chat(ChatDTO dto) {
        System.out.println("dto = " + dto);
//        return chatModel.call(dto.message());
//        return geminiChatModel.call(dto.message());
        switch (dto.provider()) {
            case groq -> {
//                return chatModel.call(dto.message());
                return groqChatClient.prompt().user(dto.message()).call().content();
            }
            case google -> {
//                return geminiChatModel.call(dto.message());
                return geminiChatClient.prompt().user(dto.message()).call().content();
            }
            default -> {
                throw new RuntimeException("지원하지 않는 Provider : %s".formatted(dto.provider()));
            }
        }
    }
}