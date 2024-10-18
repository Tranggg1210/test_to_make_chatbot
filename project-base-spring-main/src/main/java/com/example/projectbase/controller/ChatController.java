package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.request.ChatCreateDto;
import com.example.projectbase.service.QuestionService;
import com.example.projectbase.service.TabService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RestApiV1
@Validated
public class ChatController {
    private final TabService tabService;
    private final QuestionService questionService;

    @Tag(name = "chat-controller")
    @Operation(summary = "API create chat")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(UrlConstant.Chat.CREATE_CHAT)
    public ResponseEntity<?> createChat(@RequestBody ChatCreateDto chatCreateDto) {
        return VsResponseUtil.success(questionService.createChat(chatCreateDto));
    }

    @Tag(name = "chat-controller")
    @Operation(summary = "API get chat by tab")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(UrlConstant.Chat.GET_CHATS)
    public ResponseEntity<?> getChatByTabId(@RequestParam String tabId) {
        return VsResponseUtil.success(tabService.getChatsByTabId(tabId));
    }
}
