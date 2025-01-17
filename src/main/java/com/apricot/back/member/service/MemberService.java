package com.apricot.back.member.service;

import com.apricot.back.global.exception.CustomException;
import com.apricot.back.global.exception.ErrorCode;
import com.apricot.back.global.exception.member.MemberAlreadyExistsException;
import com.apricot.back.global.exception.member.MemberNotAuthorizedException;
import com.apricot.back.global.exception.member.MemberNotFoundException;
import com.apricot.back.global.exception.member.PasswordNotMatchedException;
import com.apricot.back.global.jwt.TokenDto;
import com.apricot.back.global.jwt.TokenProvider;
import com.apricot.back.member.dto.MemberRequestDto;
import com.apricot.back.member.entity.Member;
import com.apricot.back.member.enums.MemberGrade;
import com.apricot.back.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public long signUp(MemberRequestDto.SignUp requestDto) {
        String email = requestDto.getEmail();
        String nickname = requestDto.getNickname();
        Optional<Member> existedMember = memberRepository.findByEmailOrNickname(email, nickname);

        if (existedMember.isPresent()) {
            throw new MemberAlreadyExistsException();
        }

        Member member = Member.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .name(requestDto.getName())
                .nickname(requestDto.getNickname())
                .phone(requestDto.getPhone())
                .build();

        return memberRepository.save(member).getId();
    }

    @Transactional(readOnly = true)
    public TokenDto login(MemberRequestDto.Login requestDto) {
        String email = requestDto.getEmail();

        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new MemberNotFoundException()
        );

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new PasswordNotMatchedException();
        }

        return tokenProvider.generateToken(member);
    }

    @Transactional
    public void modifyMember(long memberId, MemberRequestDto.Modify requestDto, MultipartFile file, Member member) {
        Member memberToModify = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException()
        );

        if (memberToModify.getId() != member.getId() && !member.getGrade().equals(MemberGrade.OWNER)) {
            throw new MemberNotAuthorizedException();
        }

        // 프로필 이미지 처리
        String imageUrl = "";

        member.modifyInfo(requestDto.getNickname(), passwordEncoder.encode(requestDto.getPassword()), imageUrl);
    }
}
