package com.apricot.back.member.controller;

import com.apricot.back.global.jwt.TokenDto;
import com.apricot.back.member.dto.RequestDto;
import com.apricot.back.member.entity.Member;
import com.apricot.back.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestPart RequestDto.SignUp requestDto, @RequestPart(required = false) MultipartFile file) {
        memberService.signUp(requestDto, file);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestDto.Login requestDto) {
        TokenDto response = memberService.login(requestDto);

        return ResponseEntity.ok(response);
    }

}
