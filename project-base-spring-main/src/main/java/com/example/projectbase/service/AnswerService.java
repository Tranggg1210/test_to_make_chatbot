package com.example.projectbase.service;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.AnswerCreateDto;
import com.example.projectbase.domain.dto.request.AnswerUpdateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.entity.Answer;
import org.springframework.data.domain.Page;

public interface AnswerService {
    Answer createAnswer(AnswerCreateDto answerCreateDto);
    PaginationResponseDto<Answer> getAnswerByQuestionId(String questionId, PaginationFullRequestDto request);
    Answer updateAnswer(AnswerUpdateDto answerUpdateDto);
    CommonResponseDto deleteAnswer(String answerId);
}
