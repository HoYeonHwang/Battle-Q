package com.battleq;

import com.battleq.member.domain.entity.Authority;
import com.battleq.member.domain.entity.EmailAuth;
import com.battleq.member.domain.entity.Member;
import com.battleq.quiz.domain.entity.Quiz;
import com.battleq.quizItem.domain.QuizType;
import com.battleq.quizItem.domain.entity.QuizItem;
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
            for(int i=0;i<5;i++){
                Member member = Member.builder()
                        .userName("test_userName"+(i+1))
                        .email("test"+(i+1)+"@naver.com")
                        .pwd("test123")
                        .regDate(LocalDateTime.now())
                        .modDate(LocalDateTime.now())
                        .emailAuth(EmailAuth.Y)
                        .nickname("test_nickName"+i)
                        .userInfo("테스트 유저입니다.")
                        .authority(Authority.ROLE_ADMIN).build();
                em.persist(member);

                Quiz quiz  = new Quiz();
                quiz.setName("NO."+(i+1)+" 테스트용 Quiz ");
                quiz.setIntroduction("테스트용 퀴즈입니다.");
                quiz.setCategory("IT/인터넷");
                quiz.setQuizDate(LocalDateTime.now());
                quiz.setThumbnail("https://blog.kakaocdn.net/dn/0mySg/btqCUccOGVk/nQ68nZiNKoIEGNJkooELF1/img.jpg");
                quiz.setView(0);
                quiz.setMember(member);
                em.persist(quiz);

                for(int j=0;j<3;j++) {
                    QuizItem quizItem = new QuizItem();
                    quizItem.setTitle("NO."+(i+1)+"-"+ (j+1)+"테스트용 QuizItem ");
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
    }
}