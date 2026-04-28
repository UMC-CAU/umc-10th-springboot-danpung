package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;

public class ReviewConverter {

    public static ReviewResDTO.CreateResultDTO toCreateReviewResultDTO(ReviewReqDTO.PostReviewDTO request) {
        return ReviewResDTO.CreateResultDTO.builder()
                .userID(23L)
                .reviewId(101L)
                .storeName(request.getStoreName())
                .context(request.getContext())
                .stars(request.getStars())
                .images(request.getImages())
                .build();
    }
}