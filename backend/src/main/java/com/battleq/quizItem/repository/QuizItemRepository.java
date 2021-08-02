package com.battleq.quizItem.repository;

import com.battleq.quiz.domain.Quiz;
import com.battleq.quizItem.domain.QuizItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuizItemRepository {
    private final EntityManager em;


    public void save(QuizItem quizItem){
        if(quizItem.getId() == null){
            em.persist(quizItem);
        }else {
            em.merge(quizItem);
        }
    }

    public QuizItem findOne(Long id){
        return em.find(QuizItem.class, id);
    }

    public List<QuizItem> findAllByQuizId(Long id){
        return em.createQuery("select q from QuizItem q where q.id = :id", QuizItem.class)
                .setParameter("id",id)
                .getResultList();
    }

    public List<QuizItem> findAll() {
        return em.createQuery("select q from QuizItem q",QuizItem.class)
                .getResultList();
    }
}
