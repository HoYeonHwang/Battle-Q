package com.battleq.quiz.domain.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public  class UpdateQuizRequest{
    @NotEmpty
    private String name;
    private String category;
    private String thumbnail;
    private String introduction;
}
