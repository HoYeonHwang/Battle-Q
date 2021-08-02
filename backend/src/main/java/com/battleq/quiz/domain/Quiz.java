package com.battleq.quiz.domain;

import com.battleq.quiz.controller.QuizApiController;
import com.battleq.quizItem.domain.QuizItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * id : 식별자
 * name : 미션 제목
 * category : 미션 분류
 * thumbnail : 썸네일
 * introduction : 소개
 * quizDate : 생성 시간
 * view : 미션 조회수
 */
@Entity
@Getter @Setter
@Table(name = "quizs")
public class Quiz {
    @Id
    @GeneratedValue
    @Column(name = "quiz_id")
    private Long id;

    @NotEmpty
    private String name;

    //@JsonIgnore
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<QuizItem> quizItems = new ArrayList<>();

    private String category;
    private String thumbnail;
    private String introduction;
    private LocalDateTime quizDate;
    private int view;

    //private int level;


    /**
     * 퀴즈 아이템 연관관계
      */
    public void addQuizItem(QuizItem quizItem){
        quizItems.add(quizItem);
        quizItem.setQuiz(this);
    }

    /**
     * 초기 퀴즈 셋팅
     * @param name
     * @param thumbnail
     * @param introduction
     * @param category
     * @return
     */
    public static Quiz initQuiz(String name, String thumbnail, String introduction, String category){
        Quiz quiz = new Quiz();

        quiz.setName(name);
        quiz.setThumbnail(thumbnail);
        quiz.setIntroduction(introduction);
        quiz.setCategory(category);
        quiz.setView(0);
        quiz.setQuizDate(LocalDateTime.now());

        return quiz;
    }

    /*public static Quiz createQuiz(Quiz quiz, QuizItem... quizItems){

    }*/


    /**
     * 멤버 , 좋아요 추가
     */


    /**
     * 조회수 증가
     */
    public void addView(){
        this.view++;
    }
}
