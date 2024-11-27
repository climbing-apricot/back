package com.apricot.back.member.dto;

import com.apricot.back.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MemberRequestDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUp {
        private String email;
        private String password;
        private String name;
        private String nickname;
        private String phone;

        public Member toMember() {
            return Member.builder()
                    .email(email)
                    .password(password)
                    .name(name)
                    .nickname(nickname)
                    .phone(phone)
                    .build();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Login {
        private String email;
        private String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {
        private String nickname;
        private String password;
    }
}
