package com.battleq.quiz.domain.entity;

import com.battleq.member.domain.entity.Member;
import com.battleq.quizItem.domain.entity.QuizItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

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
@Getter
@Builder
@Table(name = "quizs")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long id;

    @NotEmpty
    private String name;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<QuizItem> quizItems = new ArrayList<>();

    private String category;
    private String thumbnail;
    private String introduction;
    private LocalDateTime creationDate;
    private int view;

    //private int level;

    public void addQuizItem(QuizItem quizItem){
        quizItems.add(quizItem);
        quizItem.setQuiz(this);
    }

    public void setMember(Member member){
        this.member = member;
        member.setQuiz(this);
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

    public void addView(){
        this.view++;
    }
}
