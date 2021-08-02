package com.battleq.quiz.service;

import com.battleq.quiz.domain.Quiz;
import com.battleq.quiz.repository.QuizRepository;
import com.battleq.quizItem.repository.QuizItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizItemRepository quizItemRepository;

    /**
     * 생성
     */
    @Transactional
    public Long saveQuiz(Quiz quizRequest){

        Quiz quiz = Quiz.initQuiz(quizRequest.getName(),quizRequest.getThumbnail(),quizRequest.getIntroduction(), quizRequest.getCategory());

        // 퀴즈 저장
        quizRepository.save(quiz);
        return quiz.getId();
    }
    /**
     * 수정
     */
    @Transactional
    public void update(Long id, Quiz quizRequest){
        //영속 상태
        Quiz quiz = quizRepository.findOne(id);
        quiz.setName(quizRequest.getName());
        quiz.setCategory(quizRequest.getCategory());
        quiz.setThumbnail(quizRequest.getThumbnail());
        quiz.setIntroduction(quizRequest.getIntroduction());
    }
    public Quiz findOne(Long quizId){
        return quizRepository.findOne(quizId);
    }

    public List<Quiz> findAllQuiz(){
        return quizRepository.findAll();
    }
}
