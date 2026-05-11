package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

public class ReviewReqDTO {
    @Builder
    public record PostReviewDTO(
            @NotNull
            Long memberId,

            @NotNull
            Long storeId,

            @NotBlank
            String content,

            @NotNull
            Double star,

            List<@NotBlank String> images
            ){}

    public record CreateReplyDTO(
            @NotBlank String content
    ) {}
}
