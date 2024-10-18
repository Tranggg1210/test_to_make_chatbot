package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.request.AnswerCreateDto;
import com.example.projectbase.domain.dto.request.AnswerUpdateDto;
import com.example.projectbase.service.AnswerService;
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
public class AnswerController {
    private final AnswerService answerService;

    @Tags({@Tag(name = "answer-controller-admin"), @Tag(name = "answer-controller")})
    @Operation(summary = "API create answer")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(UrlConstant.Answer.CREATE_ANSWER)
    public ResponseEntity<?> createAnswer(@RequestBody AnswerCreateDto answerCreateDto) {
        return VsResponseUtil.success(answerService.createAnswer(answerCreateDto));
    }

    @Tags({@Tag(name = "answer-controller-admin"), @Tag(name = "answer-controller")})
    @Operation(summary = "API get answer by questions")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(UrlConstant.Answer.GET_ANSWERS)
    public ResponseEntity<?> getAnswerByQuestionId(@RequestParam String questionId) {
        return VsResponseUtil.success(answerService.getAnswerByQuestionId(questionId));
    }

    @Tags({@Tag(name = "answer-controller-admin"), @Tag(name = "answer-controller")})
    @Operation(summary = "API update answer content")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping(UrlConstant.Answer.UPDATE_ANSWER)
    public ResponseEntity<?> changeAnswer(@RequestBody AnswerUpdateDto answerUpdateDto) {
        return VsResponseUtil.success(answerService.updateAnswer(answerUpdateDto));
    }

    @Tag(name = "answer-controller-admin")
    @Operation(summary = "API delete answer")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(UrlConstant.Answer.DELETE_ANSWER)
    public ResponseEntity<?> DeleteAnswerById(@RequestParam String answerId) {
        return VsResponseUtil.success(answerService.deleteAnswer(answerId));
    }
}
