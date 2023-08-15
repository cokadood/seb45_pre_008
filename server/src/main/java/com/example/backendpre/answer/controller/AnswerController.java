package com.example.backendpre.answer.controller;

import com.example.backendpre.answer.dto.AnswerPostDto;
import com.example.backendpre.answer.entity.Answer;
import com.example.backendpre.answer.mapper.AnswerMapper;
import com.example.backendpre.answer.service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/answers")
@Validated
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper mapper;

    public AnswerController(AnswerService answerService, AnswerMapper mapper) {
        this.answerService = answerService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postAnswer(
            @Valid @RequestBody AnswerPostDto answerPostDto) {
        Answer answer = answerService.createAnswer(mapper.answerPostDtoToAnswer(answerPostDto));

        return ResponseEntity.ok(mapper.answerToAnswerResponseDto(answer));
    }

}
