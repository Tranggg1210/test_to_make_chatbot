package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.ResponeConstant;
import com.example.projectbase.constant.ValidationErrorMessages;
import com.example.projectbase.domain.dto.request.TabCreateDto;
import com.example.projectbase.domain.dto.request.TabRenameDto;
import com.example.projectbase.domain.dto.response.ChatResponseDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.QuestionResponseDto;
import com.example.projectbase.domain.dto.response.TabResponseDto;
import com.example.projectbase.domain.entity.Tab;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.domain.mapper.TabMapper;
import com.example.projectbase.exception.MaxTabsCreatedException;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.TabRepository;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.service.AnswerService;
import com.example.projectbase.service.QuestionService;
import com.example.projectbase.service.TabService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TabServiceImpl implements TabService {
    private final TabRepository tabRepository;
    private final UserRepository userRepository;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final TabMapper tabMapper;

    @Override
    public TabResponseDto createTab(TabCreateDto tabCreateDto) {
        User user = userRepository.findById(tabCreateDto.getUser_id())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID));
        Tab tab = tabMapper.toEntity(tabCreateDto);
        tab.setNumberOfQuestions(0);
        tab.setUser(user);
        if (user.getNumberOfTabs() >=15) {
            throw new MaxTabsCreatedException(ValidationErrorMessages.MAX_TABS_CREATED);
        }
        user.setNumberOfTabs(user.getNumberOfTabs() + 1);
        userRepository.save(user);
        return tabMapper.toTabResponseDto(tabRepository.save(tab));
    }

    @Override
    public List<TabResponseDto> getTabsByUserId(String userId) {
        List<Tab> tabs= tabRepository.findAllByUserId(userId);
        return tabs.stream().map(tabMapper::toTabResponseDto).toList();
    }

    @Override
    public List<ChatResponseDto> getChatsByTabId(String tabId) {
        List<QuestionResponseDto> questions= questionService.getQuestionByTabId(tabId);
        List<ChatResponseDto> chatResponses = new ArrayList<>();
        questions.forEach(question -> {
                ChatResponseDto chatResponseDto = new ChatResponseDto();
                chatResponseDto.setQuestion(question);
                chatResponseDto.setAnswer(answerService.getAnswerByQuestionId(question.getId()));
                chatResponses.add(chatResponseDto);
                }
        );
        return chatResponses;
    }

    @Override
    public TabResponseDto renameTab(TabRenameDto tabRenameDto) {
        Tab tab = tabRepository.findById(tabRenameDto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Tab.ERR_NOT_FOUND_ID));
        tab.setName(tabRenameDto.getName());
        return tabMapper.toTabResponseDto(tabRepository.save(tab));
    }

    @Override
    public CommonResponseDto deleteTab(String tabId) {
        Tab tab = tabRepository.findById(tabId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Tab.ERR_NOT_FOUND_ID));
        tabRepository.delete(tab);
        return  new CommonResponseDto(true, ResponeConstant.SUCCESS);
    }
}
