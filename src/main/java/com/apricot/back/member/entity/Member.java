package com.apricot.back.member.entity;

import com.apricot.back.global.audit.TimeStamped;
import com.apricot.back.member.enums.MemberGrade;
import com.apricot.back.member.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String password;

    private String name;

    private String nickname;

    private String phone;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    private MemberGrade grade;

    @Builder
    public Member(String email, String password, String name, String nickname, String phone, String imageUrl) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.status = MemberStatus.ACTIVE;
        this.grade = MemberGrade.MEMBER;
    }
}
