package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.ResponeConstant;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.pagination.PagingMeta;
import com.example.projectbase.domain.dto.request.AnswerCreateDto;
import com.example.projectbase.domain.dto.request.AnswerUpdateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.entity.Answer;
import com.example.projectbase.domain.entity.Question;
import com.example.projectbase.domain.mapper.AnswerMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.AnswerRepository;
import com.example.projectbase.repository.QuestionRepository;
import com.example.projectbase.service.AnswerService;
import com.example.projectbase.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    @Override
    public Answer createAnswer(AnswerCreateDto answerCreateDto) {
        Question question = questionRepository.findById(answerCreateDto.getQuestionId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Answer.ERR_NOT_FOUND_ID));
        Answer answer = answerMapper.toEntity(answerCreateDto);
        answer.setCreatedTime(LocalDateTime.now());
        answer.setQuestion(question);
        return answerRepository.save(answer);
    }

    @Override
    public PaginationResponseDto<Answer> getAnswerByQuestionId(String questionId, PaginationFullRequestDto request) {
        Pageable pageable = PaginationUtil.buildPageable(request);
        Page<Answer> answerPage = answerRepository.findAnswersByQuestionId(questionId, pageable);
        PagingMeta pagingMeta = new PagingMeta(
                answerPage.getTotalElements(),
                answerPage.getTotalPages(),
                answerPage.getNumber(),
                answerPage.getSize(),
                request.getSortBy(),
                request.getIsAscending().toString()
        );
        return new PaginationResponseDto<>(pagingMeta, answerPage.toList());
    }

    @Override
    public Answer updateAnswer(AnswerUpdateDto answerUpdateDto) {
        Answer answer = answerRepository.findById(answerUpdateDto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Answer.ERR_NOT_FOUND_ID));
        answer.setContent(answerUpdateDto.getContent());
        return answerRepository.save(answer);
    }

    @Override
    public CommonResponseDto deleteAnswer(String answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Answer.ERR_NOT_FOUND_ID));
        answerRepository.delete(answer);
        return new CommonResponseDto(true, ResponeConstant.SUCCESS);
    }
}
