package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {
    @Builder
    public record CreateResultDTO(
            Long userId,
            Long reviewId,
            String storeName,
            String context,
            Integer stars,
            List<String> images){}
    @Builder
    public record ReplyResultDTO(
            Long reviewId,
            Long replyId,
            String content
    ) {}
    @Builder
    public record StoreReviewDTO(
            Long  reviewId,
            String memberName,
            String content,
            Double star,
            LocalDateTime createdAt,
            String replyContent
    ){}
}
