package com.battleq.quiz.controller;

import com.battleq.quiz.domain.dto.QuizDto;
import com.battleq.quiz.domain.dto.QuizMapper;
import com.battleq.quiz.domain.dto.request.CreateQuizRequest;
import com.battleq.quiz.domain.dto.request.UpdateQuizRequest;
import com.battleq.quiz.domain.dto.response.CreateQuizResponse;
import com.battleq.quiz.domain.dto.response.QuizListResponse;

import com.battleq.quiz.domain.dto.response.QuizResponse;
import com.battleq.quiz.domain.dto.response.UpdateQuizResponse;
import com.battleq.quiz.domain.entity.Quiz;
import com.battleq.quiz.service.QuizService;
import com.battleq.quizItem.domain.entity.QuizItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class QuizApiController {

    private final QuizService quizService;

    @GetMapping("api/v1/quiz")
    public QuizListResponse findAllQuizV1(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "10") int limit){

        List<QuizDto> resultQuizzes = quizService.findAllQuiz(offset, limit).stream()
                .map(m -> new QuizDto(m))
                .collect(Collectors.toList());

        return new QuizListResponse(HttpStatus.OK, resultQuizzes,"퀴즈 전체 검색");
    }

    @GetMapping("api/v1/quiz/{quizId}")
    public QuizResponse findOneQuizV1(@PathVariable("quizId") Long quizId){

        QuizDto quizDto = new QuizDto(quizService.findOne(quizId));

        return new QuizResponse(HttpStatus.OK, quizDto,"퀴즈 단일 검색");
    }
    @PostMapping("api/v1/quiz")
    public CreateQuizResponse saveQuizV1(@RequestBody @Valid CreateQuizRequest request){

        QuizDto dto = QuizMapper.INSTANCE.createQuizRequestToDto(request);

        Long id = quizService.saveQuiz(dto);
        return new CreateQuizResponse(id);
    }

    @PutMapping("api/v1/quiz/{quizId}")
    public UpdateQuizResponse updateQuizFormV1(@PathVariable("quizId") Long quizId, @RequestBody @Valid UpdateQuizRequest request){

        Quiz quiz = new Quiz();
        quiz.setName(request.getName());
        quiz.setCategory(request.getCategory());
        quiz.setIntroduction(request.getIntroduction());
        quiz.setThumbnail(request.getThumbnail());

        quizService.update(quizId,quiz);
        Quiz findQuiz = quizService.findOne(quizId);
        return new UpdateQuizResponse(findQuiz.getId(), findQuiz.getName());
    }

}
