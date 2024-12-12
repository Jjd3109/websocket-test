package com.example.websockettest.controller;


import com.example.websockettest.request.ChatMessageRequest;
import com.example.websockettest.response.ChatMessageResponse;
import com.example.websockettest.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Message {

    private final ChatService chatService;

//    @PostMapping("/chat")
//    public ResponseEntity<Void> sendMessage(ChatMessageRequest request) {
//        chatService.doSomething(reuqest);
//        return ResponseEntity.ok().build();
//
//    }

    @MessageMapping("/chat")
    @SendTo("/subscribe/chat")
    public ChatMessageResponse sendMessage(ChatMessageRequest request){

        log.info("request.username() 값 = {}", request.username());
        log.info("request.content() 값 = {}", request.content());
        return new ChatMessageResponse(request.username(), request.content());
    }
//
//    @MessageMapping("/chat.{chatRoomId}")
//    @SendTo("/publish/chat.{chatRoomId}")
//    public ChatMessageResponse sendMessage(ChatMessageRequest request, @DestinationVariable Long chatRoomId){
//
//        log.info("request.username() 값 = {}", request.username());
//        log.info("request.content() 값 = {}", request.content());
//        return new ChatMessageResponse(request.username(), request.content());
//    }


    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("새로운 웹소켓 연결이 생성되었습니다!");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        log.info("웹소켓 연결이 종료되었습니다!");
    }

}
