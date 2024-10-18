package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.TabCreateDto;
import com.example.projectbase.domain.dto.request.TabRenameDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.TabResponseDto;
import com.example.projectbase.domain.entity.Tab;

import java.util.List;

public interface TabService {
    TabResponseDto createTab(TabCreateDto tabCreateDto);
    List<TabResponseDto> getTabsByUserId(String userId);
    TabResponseDto renameTab(TabRenameDto tabRenameDto);
    TabResponseDto addQuestionToTab(String tabId);
    CommonResponseDto deleteTab(String tabId);
}
