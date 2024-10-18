package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.QuestionCreateDto;
import com.example.projectbase.domain.entity.Question;
import com.example.projectbase.domain.entity.Question.QuestionBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-18T10:40:01+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.12 (Ubuntu)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question toEntity(QuestionCreateDto questionCreateDto) {
        if ( questionCreateDto == null ) {
            return null;
        }

        QuestionBuilder question = Question.builder();

        question.content( questionCreateDto.getContent() );

        return question.build();
    }
}
