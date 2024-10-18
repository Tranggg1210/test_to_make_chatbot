package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.request.QuestionCreateDto;
import com.example.projectbase.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestApiV1
@Validated
public class QuestionController {
    private final QuestionService questionService;

    @Tags({@Tag(name = "question-controller-admin"), @Tag(name = "question-controller")})
    @Operation(summary = "API create question")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(UrlConstant.Question.CREATE_QUESTION)
    public ResponseEntity<?> createQuestion(@RequestBody QuestionCreateDto questionCreateDto) {
        return VsResponseUtil.success(questionService.createQuestion(questionCreateDto));
    }

    @Tags({@Tag(name = "question-controller-admin"), @Tag(name = "question-controller")})
    @Operation(summary = "API get questions by tab")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(UrlConstant.Question.GET_QUESTIONS)
    public ResponseEntity<?> getQuestions(@RequestParam String tabId) {
        return VsResponseUtil.success(questionService.getQuestionByTabId(tabId));
    }

    @Tag(name = "question-controller-admin")
    @Operation(summary = "API delete question")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(UrlConstant.Question.DELETE_QUESTION)
    public ResponseEntity<?> DeleteQuestionById(@RequestParam String questionId) {
        return VsResponseUtil.success(questionService.deleteQuestion(questionId));
    }
}
