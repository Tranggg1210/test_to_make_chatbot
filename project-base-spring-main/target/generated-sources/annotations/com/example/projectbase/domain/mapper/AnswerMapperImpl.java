package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.AnswerCreateDto;
import com.example.projectbase.domain.dto.response.AnswerResponseDto;
import com.example.projectbase.domain.entity.Answer;
import com.example.projectbase.domain.entity.Answer.AnswerBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-18T23:53:52+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.12 (Ubuntu)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer toEntity(AnswerCreateDto answerCreateDto) {
        if ( answerCreateDto == null ) {
            return null;
        }

        AnswerBuilder answer = Answer.builder();

        answer.content( answerCreateDto.getContent() );

        return answer.build();
    }

    @Override
    public AnswerResponseDto toAnswerResponseDto(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerResponseDto answerResponseDto = new AnswerResponseDto();

        answerResponseDto.setId( answer.getId() );
        answerResponseDto.setContent( answer.getContent() );

        return answerResponseDto;
    }
}
