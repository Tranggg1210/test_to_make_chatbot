package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.ResponeConstant;
import com.example.projectbase.domain.dto.request.QuestionCreateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.entity.Question;
import com.example.projectbase.domain.entity.Tab;
import com.example.projectbase.domain.mapper.QuestionMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.QuestionRepository;
import com.example.projectbase.repository.TabRepository;
import com.example.projectbase.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final TabRepository tabRepository;
    private final QuestionMapper questionMapper;

    @Override
    public Question createQuestion(QuestionCreateDto questionCreateDto) {
        Tab tab = tabRepository.findById(questionCreateDto.getTab_id())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Tab.ERR_NOT_FOUND_ID));
        Question question = questionMapper.toEntity(questionCreateDto);
        question.setTab(tab);
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getQuestionByTabId(String tabId) {
        return questionRepository.findAllByTabId(tabId);
    }


    @Override
    public CommonResponseDto deleteQuestion(String questionId) {
        Optional<Question> question= Optional.ofNullable(questionRepository.findById(questionId).orElseThrow(() ->
                new NotFoundException(ErrorMessage.Question.ERR_NOT_FOUND_ID, new String[]{questionId})));
        questionRepository.deleteById(questionId);
        return  new CommonResponseDto(true, ResponeConstant.SUCCESS);
    }
}
