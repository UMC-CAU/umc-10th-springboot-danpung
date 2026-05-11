package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("""
    select r
    from Review r
    where r.store.storeId = :storeId
    order by r.createdAt desc
""")

    Page<Review> findByStoreId(@Param("storeId") Long storeId, Pageable pageable);
    Long countByMember_MemberId(Long memberId);

    Slice<Review> findReviewsByMember_MemberIdOrderByReviewIdDesc(Long memberId, Pageable pageable);

    Slice<Review> findReviewsByMember_MemberIdAndReviewIdLessThanOrderByReviewIdDesc(
            Long memberId,
            Long reviewId,
            Pageable pageable
    );

    @Query("""
    select r
    from Review r
    where r.member.memberId = :memberId
    order by r.star desc, r.reviewId desc
""")
    Slice<Review> findMyReviewsOrderByStar(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    @Query("""
    select r
    from Review r
    where r.member.memberId = :memberId
    and (
        r.star < :star
        or (r.star = :star and r.reviewId < :reviewId)
    )
    order by r.star desc, r.reviewId desc
""")
    Slice<Review> findMyReviewsOrderByStarWithCursor(
            @Param("memberId") Long memberId,
            @Param("star") Double star,
            @Param("reviewId") Long reviewId,
            Pageable pageable
    );
}
