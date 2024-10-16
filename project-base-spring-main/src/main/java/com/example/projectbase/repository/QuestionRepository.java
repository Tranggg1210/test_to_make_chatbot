package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String>{
    Page<Question> findQuestionsByUserId(String userId, Pageable pageable);
}
