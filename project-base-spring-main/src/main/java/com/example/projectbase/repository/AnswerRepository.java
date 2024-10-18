package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,String> {
    Answer findAnswersByQuestionId(String questionId);
}
