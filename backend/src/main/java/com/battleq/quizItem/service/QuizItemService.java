package com.battleq.quizItem.service;

import com.battleq.quiz.repository.QuizRepository;
import com.battleq.quizItem.domain.entity.QuizItem;
import com.battleq.quizItem.repository.QuizItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizItemService {
    private final QuizItemRepository quizItemRepository;
    private final QuizRepository quizRepository;

    /**
     * 생성
     */
    @Transactional
    public Long saveQuizItem(QuizItem quizItemRequest){
        QuizItem quizItem = QuizItem.createQuizItem(quizItemRequest.getTitle(), quizItemRequest.getContent(), quizItemRequest.getImage(), quizItemRequest.getType(), quizItemRequest.getLimitTime(), quizItemRequest.getPoint(), quizItemRequest.getPointType());

        quizItemRepository.save(quizItem);
        return quizItem.getId();
    }

    /**
     * 수정
     */
    @Transactional
    public void update(Long id, QuizItem quizRequest){
        QuizItem quizItem = quizItemRepository.findOne(id);

        quizItem.setTitle(quizRequest.getTitle());
        quizItem.setContent(quizRequest.getContent());
        quizItem.setLimitTime(quizRequest.getLimitTime());
        quizItem.setType(quizRequest.getType());
        quizItem.setPoint(quizRequest.getPoint());
        quizItem.setPointType(quizRequest.getPointType());
        quizItem.setImage(quizRequest.getImage());
    }

    public QuizItem findOne(Long quizItemId){
        return quizItemRepository.findOne(quizItemId);
    }
    public List<QuizItem> findAllQuizItem(){
        return quizItemRepository.findAll();
    }
}
