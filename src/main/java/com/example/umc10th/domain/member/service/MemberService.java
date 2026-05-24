package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResDTO.SignupResultDTO signUp(MemberReqDTO.SignupReqDTO request) {
        Member member = MemberConverter.toMember(
                request,
                passwordEncoder.encode(request.password())
        );

        Member savedMember = memberRepository.save(member);

        return MemberConverter.toSignupResultDTO(savedMember);
    }

    public MemberResDTO.HomeDTO getHome() {
        return MemberConverter.toHomeDTO();
    }

    public MemberResDTO.MyPageDTO getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Long reviewCount = reviewRepository.countByMember_MemberId(memberId);

        return MemberConverter.toMyPageDTO(member, reviewCount);
    }
}
