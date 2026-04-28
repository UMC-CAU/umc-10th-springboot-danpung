package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReviewResDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResultDTO{
        Long userID;
        Long reviewId;
        String storeName;
        String context;
        Integer stars;
        List<String> images;
    }
}
