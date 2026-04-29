package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;

public class ReviewConverter {

    public static ReviewResDTO.CreateResultDTO toCreateReviewResultDTO(ReviewReqDTO.PostReviewDTO request) {
        return ReviewResDTO.CreateResultDTO.builder()
                .userId(23L)
                .reviewId(101L)
                .storeName(request.storeName())
                .context(request.context())
                .stars(request.stars())
                .images(request.images())
                .build();
    }
}