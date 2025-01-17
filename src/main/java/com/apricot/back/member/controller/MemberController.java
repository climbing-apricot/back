package com.apricot.back.member.controller;

import com.apricot.back.global.jwt.TokenDto;
import com.apricot.back.global.utils.UriCreator;
import com.apricot.back.member.dto.MemberRequestDto;
import com.apricot.back.member.entity.Member;
import com.apricot.back.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody MemberRequestDto.SignUp requestDto) {
        long memberId = memberService.signUp(requestDto);
        return ResponseEntity.created(UriCreator.createURI(memberId)).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberRequestDto.Login requestDto) {
        TokenDto response = memberService.login(requestDto);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<?> modifyMember(@PathVariable long memberId,
                                          @RequestPart(required = false) MemberRequestDto.Modify requestDto,
                                          @RequestPart(required = false) MultipartFile file,
                                          @AuthenticationPrincipal Member member) {
        memberService.modifyMember(memberId, requestDto, file, member);

        return ResponseEntity.noContent().build();
    }

}
