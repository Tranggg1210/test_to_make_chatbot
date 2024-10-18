package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.AnswerCreateDto;
import com.example.projectbase.domain.dto.request.AnswerUpdateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.entity.Answer;

public interface AnswerService {
    Answer createAnswer(AnswerCreateDto answerCreateDto);
    Answer getAnswerByQuestionId(String questionId);
    Answer updateAnswer(AnswerUpdateDto answerUpdateDto);
    CommonResponseDto deleteAnswer(String answerId);
}
