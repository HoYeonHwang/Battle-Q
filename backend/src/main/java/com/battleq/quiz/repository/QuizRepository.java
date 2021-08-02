package com.battleq.quiz.repository;

import com.battleq.quiz.domain.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuizRepository {

    private final EntityManager em;

    /**
     * 병합 변경해야합니다.
     * @param quiz
     */
    public void save(Quiz quiz){
        if(quiz.getId() == null){
            em.persist(quiz);
        }else {
            em.merge(quiz);
        }
    }

    public Quiz findOne(Long id){
        return em.find(Quiz.class, id);
    }

    public List<Quiz> findAll() {
        return em.createQuery("select q from Quiz q",Quiz.class)
                .getResultList();
    }
}
