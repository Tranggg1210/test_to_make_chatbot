package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.QuestionCreateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.entity.Question;

import java.util.List;

public interface QuestionService {
    Question createQuestion(QuestionCreateDto questionCreateDto);
    List<Question> getQuestionByTabId(String tabId);
    CommonResponseDto deleteQuestion(String questionId);
}
