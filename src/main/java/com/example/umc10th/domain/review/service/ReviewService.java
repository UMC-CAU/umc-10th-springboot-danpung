package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Reply;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReplyRepository;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResDTO.CreateResultDTO createReview(ReviewReqDTO.PostReviewDTO request){
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .content(request.content())
                .star(request.star())
                .build();

        Review savedReview = reviewRepository.save(review);
        return ReviewResDTO.CreateResultDTO.builder()
                .userId(member.getMemberId())
                .reviewId(savedReview.getReviewId())
                .storeName(store.getName())
                .context(savedReview.getContent())
                .stars(savedReview.getStar().intValue())
                .images(null)
                .build();
    }
    @Transactional
    public ReviewResDTO.ReplyResultDTO createReply(Long reviewId, ReviewReqDTO.CreateReplyDTO request) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));

        Reply reply = Reply.builder()
                .content(request.content())
                .build();

        Reply savedReply = replyRepository.save(reply);

        review.addReply(savedReply);

        return ReviewResDTO.ReplyResultDTO.builder()
                .reviewId(review.getReviewId())
                .replyId(savedReply.getReplyId())
                .content(savedReply.getContent())
                .build();
}
    public Page<ReviewResDTO.StoreReviewDTO> getStoreReviews(
            Long storeId,
            Pageable pageable
    ) {
        Page<Review> reviews = reviewRepository.findByStoreId(storeId, pageable);

        return reviews.map(review -> ReviewResDTO.StoreReviewDTO.builder()
                .reviewId(review.getReviewId())
                .memberName(review.getMember().getName())
                .content(review.getContent())
                .star(review.getStar())
                .createdAt(review.getCreatedAt())
                .replyContent(review.getReply() == null ? null : review.getReply().getContent())
                .build());
    }
}
