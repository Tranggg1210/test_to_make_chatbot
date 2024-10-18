package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.ResponeConstant;
import com.example.projectbase.constant.ValidationErrorMessages;
import com.example.projectbase.domain.dto.request.AnswerCreateDto;
import com.example.projectbase.domain.dto.request.ChatCreateDto;
import com.example.projectbase.domain.dto.request.QuestionCreateDto;
import com.example.projectbase.domain.dto.response.ChatResponseDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.QuestionResponseDto;
import com.example.projectbase.domain.entity.Question;
import com.example.projectbase.domain.entity.Tab;
import com.example.projectbase.domain.mapper.QuestionMapper;
import com.example.projectbase.exception.MaxQuestionsSentException;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.QuestionRepository;
import com.example.projectbase.repository.TabRepository;
import com.example.projectbase.service.AnswerService;
import com.example.projectbase.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final AnswerService answerService;
    private final QuestionRepository questionRepository;
    private final TabRepository tabRepository;
    private final QuestionMapper questionMapper;

    @Override
    public QuestionResponseDto createQuestion(QuestionCreateDto questionCreateDto) {
        Tab tab = tabRepository.findById(questionCreateDto.getTab_id())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Tab.ERR_NOT_FOUND_ID));
        Question question = questionMapper.toEntity(questionCreateDto);
        question.setTab(tab);
        if(tab.getNumberOfQuestions() >= 500) throw new MaxQuestionsSentException(ValidationErrorMessages.MAX_QUESTIONS_SENT);
        tab.setNumberOfQuestions(tab.getNumberOfQuestions() + 1);
        tabRepository.save(tab);
        return questionMapper.toQuestionResponseDto(questionRepository.save(question));
    }

    @Override
    public ChatResponseDto createChat(ChatCreateDto chatCreateDto) {
        Tab tab = tabRepository.findById(chatCreateDto.getTabId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Tab.ERR_NOT_FOUND_ID));
        Question question = new Question();
        question.setContent(chatCreateDto.getQuestion());
        question.setTab(tab);
        if(tab.getNumberOfQuestions() >= 500) throw new MaxQuestionsSentException(ValidationErrorMessages.MAX_QUESTIONS_SENT);
        tab.setNumberOfQuestions(tab.getNumberOfQuestions() + 1);
        tabRepository.save(tab);
        questionRepository.save(question);
        answerService.createAnswer( new AnswerCreateDto(chatCreateDto.getAnswer(), question.getId()));
        return new ChatResponseDto(
                questionMapper.toQuestionResponseDto(question),
                answerService.getAnswerByQuestionId(question.getId())
        );
    }

    @Override
    public List<QuestionResponseDto> getQuestionByTabId(String tabId) {
        List<Question> questions = questionRepository.findAllByTabId(tabId);
        return questions.stream().map(questionMapper::toQuestionResponseDto).toList();
    }


    @Override
    public CommonResponseDto deleteQuestion(String questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new NotFoundException(ErrorMessage.Question.ERR_NOT_FOUND_ID));
        questionRepository.delete(question);
        return  new CommonResponseDto(true, ResponeConstant.SUCCESS);
    }
}
