package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.ResponeConstant;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.pagination.PagingMeta;
import com.example.projectbase.domain.dto.request.QuestionCreateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.entity.Question;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.domain.mapper.QuestionMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.QuestionRepository;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.service.QuestionService;
import com.example.projectbase.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final QuestionMapper questionMapper;

    @Override
    public Question createQuestion(QuestionCreateDto questionCreateDto) {
        User user = userRepository.findById(questionCreateDto.getUserId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID));
        Question question = questionMapper.toEntity(questionCreateDto);
        question.setCreatedTime(LocalDateTime.now());
        question.setUser(user);
        return questionRepository.save(question);
    }

    @Override
    public PaginationResponseDto<Question> getQuestionByUserId(String userId, PaginationFullRequestDto request) {
        Pageable pageable = PaginationUtil.buildPageable(request);
        Page<Question> questionPage = questionRepository.findQuestionsByUserId(userId, pageable);
        PagingMeta pagingMeta = new PagingMeta(
                questionPage.getTotalElements(),
                questionPage.getTotalPages(),
                questionPage.getNumber(),
                questionPage.getSize(),
                request.getSortBy(),
                request.getIsAscending().toString()
        );
        return new PaginationResponseDto<>(pagingMeta, questionPage.toList());
    }

    @Override
    public CommonResponseDto deleteQuestion(String questionId) {
        Optional<Question> question= Optional.ofNullable(questionRepository.findById(questionId).orElseThrow(() ->
                new NotFoundException(ErrorMessage.Question.ERR_NOT_FOUND_ID, new String[]{questionId})));
        questionRepository.deleteById(questionId);
        return  new CommonResponseDto(true, ResponeConstant.SUCCESS);
    }
}
