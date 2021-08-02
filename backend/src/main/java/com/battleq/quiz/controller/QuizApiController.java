package com.battleq.quiz.controller;

import com.battleq.quiz.domain.Quiz;
import com.battleq.quiz.service.QuizService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
    public QuizListResponse findAllQuizV1(){

        List<QuizDto> resultQuizzes = quizService.findAllQuiz().stream()
                .map(m -> new QuizDto(m.getName(), m.getCategory(), m.getThumbnail(),m.getIntroduction()))
                .collect(Collectors.toList());

        return new QuizListResponse("STATUS OK", resultQuizzes,"퀴즈 전체 검색");
    }

    @GetMapping("api/v1/quiz/{quizId}")
    public QuizResponse findOneQuizV1(@PathVariable("quizId") Long quizId){

        QuizDto quizDto = new QuizDto().createQuizDto(quizService.findOne(quizId));

        return new QuizResponse("STATUS OK", quizDto,"퀴즈 단일 검색");
    }
    @PostMapping("api/v1/quiz")
    public CreateQuizResponse saveQuizV1(@RequestBody @Valid CreateQuizRequest request){


        // 고민중
        Quiz quiz = new Quiz();
        quiz.setName(request.getName());
        quiz.setCategory(request.getCategory());
        quiz.setIntroduction(request.getIntroduction());
        quiz.setThumbnail(request.getThumbnail());


        Long id = quizService.saveQuiz(quiz);
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

    @Data
    static class CreateQuizRequest{
        @NotEmpty
        private String name;
        private String category;
        private String thumbnail;
        private String introduction;
    }
    @Data
    static class CreateQuizResponse{
        private Long id;

        public CreateQuizResponse(Long id){
            this.id = id;
        }
    }

    @Data
    static  class UpdateQuizRequest{
        @NotEmpty
        private String name;
        private String category;
        private String thumbnail;
        private String introduction;
    }
    @Data
    @AllArgsConstructor
    static class UpdateQuizResponse{
        private Long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class QuizResponse<T>{
        private String status;
        private T data;
        private String message;
    }

    @Data
    @AllArgsConstructor
    static class QuizListResponse<T>{
        private String status;
        private T data;
        private String message;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class QuizDto {
        private String name;
        private String category;
        private String thumbnail;
        private String introduction;

        public QuizDto createQuizDto(Quiz quiz){
            this.name = quiz.getName();
            this.category = quiz.getCategory();
            this.thumbnail = quiz.getThumbnail();
            this.introduction = quiz.getIntroduction();

            return this;
        }
    }
}
