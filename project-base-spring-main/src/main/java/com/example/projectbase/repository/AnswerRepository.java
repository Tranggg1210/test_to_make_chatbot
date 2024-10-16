package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,String> {
    Page<Answer> findAnswersByQuestionId(String questionId, Pageable pageable);
}
