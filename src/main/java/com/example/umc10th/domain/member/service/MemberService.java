package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
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
    private final JwtUtil jwtUtil;

    @Transactional
    public MemberResDTO.SignupResultDTO signUp(MemberReqDTO.SignupReqDTO request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

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

    public MemberResDTO.MyPageDTO getMyPage(AuthMember authMember) {
        Long memberId = authMember.getMember().getMemberId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Long reviewCount = reviewRepository.countByMember_MemberId(memberId);

        return MemberConverter.toMyPageDTO(member, reviewCount);
    }

    public MemberResDTO.LoginResDTO login(MemberReqDTO.LoginReqDTO request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));

        return MemberResDTO.LoginResDTO.builder()
                .accessToken(accessToken)
                .build();
    }
}
