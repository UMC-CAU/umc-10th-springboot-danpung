package com.example.umc10th.domain.review.dto;

import lombok.Getter;

import java.util.List;

public class ReviewReqDTO {
    @Getter
    public static class PostReviewDTO{
        String storeName;
        String context;
        Integer stars;
        List<String> images;
    }
}
