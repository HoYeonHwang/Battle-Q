package com.battleq.quiz.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizListResponse<T>{
    private Object status;
    private T data;
    private String message;
}


