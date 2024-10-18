package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.ChatCreateDto;
import com.example.projectbase.domain.dto.request.QuestionCreateDto;
import com.example.projectbase.domain.dto.response.ChatResponseDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.QuestionResponseDto;

import java.util.List;

public interface QuestionService {
    QuestionResponseDto createQuestion(QuestionCreateDto questionCreateDto);
    ChatResponseDto createChat(ChatCreateDto chatCreateDto);
    List<QuestionResponseDto> getQuestionByTabId(String tabId);
    CommonResponseDto deleteQuestion(String questionId);
}
