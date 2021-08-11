package com.battleq.quizItem.controller;

import com.battleq.quizItem.domain.dto.QuizItemDto;
import com.battleq.quizItem.domain.dto.request.CreateQuizItemRequest;
import com.battleq.quizItem.domain.dto.request.UpdateQuizItemRequest;
import com.battleq.quizItem.domain.dto.response.CreateQuizItemResponse;
import com.battleq.quizItem.domain.dto.response.QuizItemListResponse;
import com.battleq.quizItem.domain.dto.response.QuizItemResponse;
import com.battleq.quizItem.domain.dto.response.UpdateQuizItemResponse;
import com.battleq.quizItem.domain.entity.QuizItem;
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
    public UpdateQuizItemResponse updateQuizItemFormV1(@PathVariable("quizItemId") Long quizItemId, @RequestBody @Valid UpdateQuizItemRequest request){
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










}
