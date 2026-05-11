package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService  {
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public MemberResDTO.SignUpResultDTO signUp(MemberReqDTO.SignUp request) {
        Member member = Member.builder()
                .name(request.name())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .gender(request.gender())
                .socialType(request.socialType())
                .point(0)
                .build();

        Member savedMember = memberRepository.save(member);

        return MemberResDTO.SignUpResultDTO.builder()
                .memberId(savedMember.getMemberId())
                .createdAt(savedMember.getCreatedAt())
                .build();
    }

    public MemberResDTO.MyPageDTO getMyPage(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(()->new IllegalArgumentException(("존재하지 않는 회원입니다.")));

        Long reviewCount = reviewRepository.countByMember_MemberId(memberId);
        return MemberResDTO.MyPageDTO.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .reviewCount(reviewCount)
                .build();
    }

}
