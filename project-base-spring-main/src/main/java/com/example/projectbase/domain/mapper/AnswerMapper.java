package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.AnswerCreateDto;
import com.example.projectbase.domain.dto.response.AnswerResponseDto;
import com.example.projectbase.domain.entity.Answer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer toEntity(AnswerCreateDto answerCreateDto);
    AnswerResponseDto toAnswerResponseDto(Answer answer);
}
