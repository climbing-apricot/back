package com.apricot.back.member.service;

import com.apricot.back.global.exception.CustomException;
import com.apricot.back.global.exception.ErrorCode;
import com.apricot.back.member.dto.RequestDto;
import com.apricot.back.member.entity.Member;
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

    @Transactional
    public void signUp(RequestDto.SignUp requestDto, MultipartFile image) {
        String email = requestDto.getEmail();
        String nickname = requestDto.getNickname();
        Optional<Member> existedMember = memberRepository.findByEmailOrNickname(email, nickname);

        if (existedMember.isPresent()) {
            throw new CustomException(ErrorCode.MEMBER_ALREADY_EXISTS);
        }

        // 이미지 저장 (String)
        String imageUrl = "";

        Member member = Member.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .name(requestDto.getName())
                .nickname(requestDto.getNickname())
                .phone(requestDto.getPhone())
                .imageUrl(imageUrl)
                .build();

        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public String login(RequestDto.Login requestDto) {
        String email = requestDto.getEmail();

        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)
        );

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCHED);
        }

        // 토큰 발행

        return null;
    }
}
