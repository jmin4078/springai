package org.example.springai.service;

import lombok.RequiredArgsConstructor;
import org.example.springai.dto.ChatDTO;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    // Could not autowire. There is more than one bean of 'ChatModel' type.
    @Qualifier("openAiChatModel") // Bean 이름으로 타입 기반 의존성 주입된 것 중 필요한 걸 지정
    private final ChatModel chatModel;

    @Qualifier("googleGenAiChatModel")
    private final ChatModel geminiChatModel;

    public String chat(ChatDTO dto) {
//        return chatModel.call(dto.message());
        return geminiChatModel.call(dto.message());
    }
}