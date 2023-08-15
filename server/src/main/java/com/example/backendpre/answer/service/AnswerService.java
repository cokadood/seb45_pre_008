package com.example.backendpre.answer.service;

import com.example.backendpre.answer.entity.Answer;
import com.example.backendpre.answer.repository.AnswerRepository;
import com.example.backendpre.exception.BusinessLogicException;
import com.example.backendpre.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getBody())
                .ifPresent(answerBody -> findAnswer.setBody(answerBody));
        Optional.ofNullable(answer.getAnswerStatus())
                .ifPresent(answerStatus -> findAnswer.setAnswerStatus(answerStatus));

        findAnswer.setModifiedAt(LocalDateTime.now());

        return answerRepository.save(findAnswer);
    }

    public Page<Answer> findAnswers(int page, int size) {
        return answerRepository.findAll(PageRequest.of(page, size, Sort.by("answerId").descending()));
    }

    private Answer findVerifiedAnswer(long answerId) {
        Optional<Answer> optionalAnswer =
                answerRepository.findById(answerId);
        Answer findAnswer =
                optionalAnswer.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return findAnswer;
    }


}
