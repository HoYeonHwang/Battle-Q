package com.battleq;

import com.battleq.quiz.domain.Quiz;
import com.battleq.quizItem.domain.QuizItem;
import com.battleq.quizItem.domain.QuizType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;
    @PostConstruct
    public void init(){
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;
        public void dbInit(){
            Quiz quiz  = new Quiz();
            quiz.setName("NO.1 테스트용 Quiz ");
            quiz.setIntroduction("테스트용 퀴즈입니다. ");
            quiz.setCategory("IT/인터넷");
            quiz.setQuizDate(LocalDateTime.now());
            quiz.setThumbnail("https://blog.kakaocdn.net/dn/0mySg/btqCUccOGVk/nQ68nZiNKoIEGNJkooELF1/img.jpg");
            quiz.setView(0);
            em.persist(quiz);

            QuizItem quizItem = new QuizItem();
            quizItem.setTitle("NO.1 테스트용 QuizItem ");
            quizItem.setContent("테스트용 퀴즈 아이템 입니다.");
            quizItem.setImage("http://t1.daumcdn.net/friends/prod/editor/dc8b3d02-a15a-4afa-a88b-989cf2a50476.jpg");
            quizItem.setType(QuizType.VOTE);
            quizItem.setLimitTime("10");
            quizItem.setPoint("100");
            quizItem.setPointType("double");

            quizItem.setQuiz(quiz);
            em.persist(quizItem);

        }
    }
}
