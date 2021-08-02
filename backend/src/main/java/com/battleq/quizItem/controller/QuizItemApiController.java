package com.battleq.quizItem.controller;

import com.battleq.quiz.controller.QuizApiController;
import com.battleq.quiz.domain.Quiz;
import com.battleq.quizItem.domain.QuizItem;
import com.battleq.quizItem.domain.QuizType;
import com.battleq.quizItem.service.QuizItemService;
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
public class QuizItemApiController {
    private final QuizItemService quizItemService;

    @GetMapping("api/v1/quizItem")
    public QuizItemListResponse findAllQuizItemV1() {
        List<QuizItemDto> resultQuizItems = quizItemService.findAllQuizItem().stream()
                .map(m -> new QuizItemDto().createQuizItemDto(m))
                .collect(Collectors.toList());

        return new QuizItemListResponse("status OK", resultQuizItems , "퀴즈 아이템 전체 검색");
    }
    @GetMapping("api/v1/quizItem/{quizItemId}")
    public QuizItemResponse findOneQuizItemV1(@PathVariable("quizItemId") Long quizItemId){
        QuizItemDto quizItemDto = new QuizItemDto().createQuizItemDto(quizItemService.findOne(quizItemId));

        return new QuizItemResponse("status OK", quizItemDto, "퀴즈 아이템 단일 검색");
    }

    @PostMapping("api/v1/quizItem")
    public CreateQuizItemResponse saveQuizItemV1(@RequestBody @Valid CreateQuizItemRequest request){

        //고민중
        QuizItem quizItem = new QuizItem();
        quizItem.setTitle(request.getTitle());
        quizItem.setContent(request.getContent());
        quizItem.setImage(request.getImage());
        quizItem.setType(request.getType());
        quizItem.setLimitTime(request.getLimitTime());
        quizItem.setPoint(request.getPoint());
        quizItem.setPointType(request.getPointType());


        Long id  = quizItemService.saveQuizItem(quizItem);
        return new CreateQuizItemResponse(id);
    }

    @PutMapping("api/v1/quizItem/{quizItemId}")
    public UpdateQuizItemResponse updateQuizItemFormV1(@PathVariable("quizItemId") Long quizItemId,@RequestBody @Valid UpdateQuizItemRequest request){
        //고민중
        QuizItem quizItem = new QuizItem();
        quizItem.setTitle(request.getTitle());
        quizItem.setContent(request.getContent());
        quizItem.setImage(request.getImage());
        quizItem.setType(request.getType());
        quizItem.setLimitTime(request.getLimitTime());
        quizItem.setPoint(request.getPoint());
        quizItem.setPointType(request.getPointType());

        quizItemService.update(quizItemId,quizItem);
        QuizItem findQuizItem = quizItemService.findOne(quizItemId);
        return new UpdateQuizItemResponse(findQuizItem.getId(), findQuizItem.getTitle());
    }

    @Data
    @AllArgsConstructor
    static class QuizItemResponse<T>{
        private String status;
        private T data;
        private String message;
    }
    @Data
    @AllArgsConstructor
    static class QuizItemListResponse<T> {
        private String status;
        private T data;
        private String message;
    }

    @Data
    static class CreateQuizItemResponse{
        private Long id;

        public CreateQuizItemResponse(Long id ){
            this.id = id;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class QuizItemDto {
        private String title;
        private String content;
        private String image;
        private QuizType type;
        private String limitTime;
        private String point;
        private String pointType;

        public QuizItemDto createQuizItemDto(QuizItem quizItem) {
            this.title = quizItem.getTitle();
            this.content = quizItem.getContent();
            this.image = quizItem.getImage();
            this.type = quizItem.getType();
            this.limitTime = quizItem.getLimitTime();
            this.point = quizItem.getPoint();
            this.pointType = quizItem.getPointType();

            return this;
        }
    }

    @Data
    static class CreateQuizItemRequest{
        @NotEmpty
        private String title;
        private String content;
        private String image;
        private QuizType type;
        private String limitTime;
        private String point;
        private String pointType;
    }

    @Data
    static class UpdateQuizItemRequest{
        @NotEmpty
        private String title;
        private String content;
        private String image;
        private QuizType type;
        private String limitTime;
        private String point;
        private String pointType;
    }
    @Data
    @AllArgsConstructor
    static class UpdateQuizItemResponse{
        private Long id;
        private String name;
    }
}
