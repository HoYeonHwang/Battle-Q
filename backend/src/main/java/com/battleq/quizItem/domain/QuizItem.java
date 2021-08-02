package com.battleq.quizItem.domain;

import com.battleq.quiz.domain.Quiz;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class QuizItem {

    @Id
    @GeneratedValue
    @Column(name = "quizItem_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private String title; //퀴즈 제목
    private String content; //퀴즈 내용
    private String image; //퀴즈 이미지
    private QuizType type; // 퀴즈 타입
    private String limitTime; // 퀴즈 제한시간
    private String point; // 퀴즈 포인트
    private String pointType; // 퀴즈 타입

    /**
     * 초기 퀴즈 아이템 생성
     */
    public static QuizItem createQuizItem(String title, String content, String image, QuizType type, String limitTime,String point, String pointType){
        QuizItem quizItem = new QuizItem();
        quizItem.setTitle(title);
        quizItem.setContent(content);
        quizItem.setImage(image);
        quizItem.setType(type);
        quizItem.setLimitTime(limitTime);
        quizItem.setPoint(point);
        quizItem.setPointType(pointType);

        return quizItem;
    }

}
