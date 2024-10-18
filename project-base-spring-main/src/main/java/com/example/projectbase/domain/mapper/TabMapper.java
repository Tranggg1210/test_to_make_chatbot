package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.TabCreateDto;
import com.example.projectbase.domain.dto.response.TabResponseDto;
import com.example.projectbase.domain.entity.Tab;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TabMapper {
    Tab toEntity(TabCreateDto tabCreateDto);
    TabResponseDto toTabResponseDto(Tab tab);
}
