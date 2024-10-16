package com.example.projectbase.service;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.QuestionCreateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.entity.Question;

public interface QuestionService {
    Question createQuestion(QuestionCreateDto questionCreateDto);
    PaginationResponseDto<Question> getQuestionByUserId(String userId, PaginationFullRequestDto request);
    CommonResponseDto deleteQuestion(String questionId);
}
