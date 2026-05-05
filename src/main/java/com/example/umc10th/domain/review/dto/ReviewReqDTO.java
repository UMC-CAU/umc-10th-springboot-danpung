package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

public class ReviewReqDTO {
    @Builder
    public record PostReviewDTO(
            @NotBlank
            String storeName,

            @NotBlank
            String context,

            @NotNull
            Integer stars,

            List<@NotBlank String> images
            ){}
}
