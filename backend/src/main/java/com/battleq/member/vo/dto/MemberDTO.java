package com.battleq.member.vo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.battleq.member.vo.entity.Member;
import com.battleq.member.vo.entity.EmailAuth;


@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String userName;
    private String email;
    private String pwd;
    private EmailAuth emailAuth;
    private String nickname;
    private String userInfo;


    public void updateEmailAuth(EmailAuth auth) {
        this.emailAuth = auth;
    }

    public void updateEncodePassword(String password) {
        this.pwd = password;
    }

    public Member toEntity() {
        return Member.builder()
                .userName(userName)
                .email(email)
                .pwd(pwd)
                .emailAuth(emailAuth)
                .nickname(nickname)
                .userInfo(userInfo)
                .build();
    }
}
