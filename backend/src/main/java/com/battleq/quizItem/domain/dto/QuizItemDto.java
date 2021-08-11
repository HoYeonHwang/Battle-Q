package com.battleq.quizItem.domain.dto;

import com.battleq.quiz.domain.entity.Quiz;
import com.battleq.quizItem.domain.QuizType;
import com.battleq.quizItem.domain.entity.QuizItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizItemDto {
    private String title;
    private String content;
    private String image;
    private QuizType type;
    private String limitTime;
    private String point;
    private String pointType;

    public QuizItemDto createQuizItemDto(QuizItem quizItem) {
        this.title = quizItem.getTitle();
        this.content = quizItem.getContent();
        this.image = quizItem.getImage();
        this.type = quizItem.getType();
        this.limitTime = quizItem.getLimitTime();
        this.point = quizItem.getPoint();
        this.pointType = quizItem.getPointType();

        return this;
    }
    public QuizItemDto(QuizItem quizItem){
        this.title = quizItem.getTitle();
        this.content = quizItem.getContent();
        this.image = quizItem.getImage();
        this.type = quizItem.getType();
        this.limitTime = quizItem.getLimitTime();
        this.point = quizItem.getPoint();
        this.pointType = quizItem.getPointType();
    }
}