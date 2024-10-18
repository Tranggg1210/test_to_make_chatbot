package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.AnswerCreateDto;
import com.example.projectbase.domain.dto.request.AnswerUpdateDto;
import com.example.projectbase.domain.dto.response.AnswerResponseDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.entity.Answer;

public interface AnswerService {
    AnswerResponseDto createAnswer(AnswerCreateDto answerCreateDto);
    AnswerResponseDto getAnswerByQuestionId(String questionId);
    AnswerResponseDto updateAnswer(AnswerUpdateDto answerUpdateDto);
    CommonResponseDto deleteAnswer(String answerId);
}
