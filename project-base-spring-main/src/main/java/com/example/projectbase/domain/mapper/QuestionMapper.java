package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.QuestionCreateDto;
import com.example.projectbase.domain.dto.response.QuestionResponseDto;
import com.example.projectbase.domain.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question toEntity(QuestionCreateDto questionCreateDto);
    QuestionResponseDto toQuestionResponseDto(Question question);
}
