package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.ResponeConstant;
import com.example.projectbase.domain.dto.request.AnswerCreateDto;
import com.example.projectbase.domain.dto.request.AnswerUpdateDto;
import com.example.projectbase.domain.dto.response.AnswerResponseDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.entity.Answer;
import com.example.projectbase.domain.entity.Question;
import com.example.projectbase.domain.mapper.AnswerMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.AnswerRepository;
import com.example.projectbase.repository.QuestionRepository;
import com.example.projectbase.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    @Override
    public AnswerResponseDto createAnswer(AnswerCreateDto answerCreateDto) {
        Question question = questionRepository.findById(answerCreateDto.getQuestionId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Answer.ERR_NOT_FOUND_ID));
        Answer answer = answerMapper.toEntity(answerCreateDto);
        answer.setQuestion(question);
        return answerMapper.toAnswerResponseDto(answerRepository.save(answer));
    }

    @Override
    public AnswerResponseDto getAnswerByQuestionId(String questionId) {
        return answerMapper.toAnswerResponseDto(answerRepository.findAnswersByQuestionId(questionId));
    }

    @Override
    public AnswerResponseDto updateAnswer(AnswerUpdateDto answerUpdateDto) {
        Answer answer = answerRepository.findById(answerUpdateDto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Answer.ERR_NOT_FOUND_ID));
        answer.setContent(answerUpdateDto.getContent());
        return answerMapper.toAnswerResponseDto(answerRepository.save(answer));
    }

    @Override
    public CommonResponseDto deleteAnswer(String answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Answer.ERR_NOT_FOUND_ID));
        answerRepository.delete(answer);
        return new CommonResponseDto(true, ResponeConstant.SUCCESS);
    }
}
