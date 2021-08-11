package com.battleq.quiz.service;

import com.battleq.member.domain.entity.Member;
import com.battleq.member.repository.MemberRepository;
import com.battleq.quiz.domain.dto.QuizDto;
import com.battleq.quiz.domain.entity.Quiz;
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
    private final MemberRepository memberRepository;

    @Transactional
    public Long saveQuiz(QuizDto quizRequest) {

        Member member = memberRepository.findOne(quizRequest.getMemberId());

        Quiz quiz = Quiz.initQuiz(quizRequest.getName(), quizRequest.getThumbnail(), quizRequest.getIntroduction(), quizRequest.getCategory());

        quizRepository.save(quiz);
        return quiz.getId();
    }

    @Transactional
    public void update(Long id, Quiz quizRequest) {
        Quiz quiz = quizRepository.findOne(id);
        quiz.setName(quizRequest.getName());
        quiz.setCategory(quizRequest.getCategory());
        quiz.setThumbnail(quizRequest.getThumbnail());
        quiz.setIntroduction(quizRequest.getIntroduction());
    }

    public Quiz findOne(Long quizId) {
        return quizRepository.findOne(quizId);
    }

    public List<Quiz> findAllQuiz(int offset, int limit) {
        return quizRepository.findAllWithMemberItem(offset, limit);
    }
}
