package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.TabCreateDto;
import com.example.projectbase.domain.dto.request.TabRenameDto;
import com.example.projectbase.domain.dto.response.ChatResponseDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.TabResponseDto;

import java.util.List;

public interface TabService {
    TabResponseDto createTab(TabCreateDto tabCreateDto);
    List<TabResponseDto> getTabsByUserId(String userId);
    List<ChatResponseDto> getChatsByTabId(String tabId);
    TabResponseDto renameTab(TabRenameDto tabRenameDto);
    CommonResponseDto deleteTab(String tabId);
}
