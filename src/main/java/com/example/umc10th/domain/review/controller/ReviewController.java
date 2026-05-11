package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<ReviewResDTO.CreateResultDTO> createReview(
            @RequestBody @Valid ReviewReqDTO.PostReviewDTO request
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.REVIEW_POST_SUCCESS,
                reviewService.createReview(request)
        );
    }

    @PostMapping("/{reviewId}/replies")
    public ApiResponse<ReviewResDTO.ReplyResultDTO> createReply(
            @PathVariable Long reviewId,
            @RequestBody @Valid ReviewReqDTO.CreateReplyDTO request
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.REPLY_POST_SUCCESS,
                reviewService.createReply(reviewId, request)
        );
    }

    @GetMapping("/stores/{storeId}")
    public ApiResponse<Page<ReviewResDTO.StoreReviewDTO>> getStoreReviews(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.REVIEW_GET_SUCCESS,
                reviewService.getStoreReviews(storeId, PageRequest.of(page, size))
        );
    }

    @GetMapping("/me")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.MyReviewDTO>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "-1") String cursor,
            @RequestParam(defaultValue = "id") String query
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.REVIEW_GET_SUCCESS,
                reviewService.getMyReviews(memberId, pageSize, cursor, query)
        );
    }
}
