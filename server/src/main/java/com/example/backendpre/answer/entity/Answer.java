package com.example.backendpre.answer.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    private long answerId;

    private String body;

    private AnswerStatus answerStatus = AnswerStatus.Answer_EXIST;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = LocalDateTime.now();

    public enum AnswerStatus {
        Answer_EXIST("이미 존재하는 답변입니다"),
        Answer_NOT_EXIST("존재하지 않는 답변입니다.");

        @Getter
        private String status;

        AnswerStatus(String status) {
            this.status = status;
        }
    }
}
