package com.battleq.member.vo.entity;

import com.battleq.member.vo.dto.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "BATTLE_MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String userName;
    @Column(unique = true,length = 500)
    private String email;
    @Column(length = 500)
    private String pwd;
    @Column
    @ColumnDefault("current_timestamp()")
    private LocalDateTime regDate;
    @Column
    @ColumnDefault("current_timestamp()")
    private LocalDateTime modDate;
    @Enumerated(EnumType.STRING)
    @Column
    private EmailAuth emailAuth;
    @Column(length = 100, nullable = false)
    private String nickname;
    @Column
    private Long profileImg;
    @Column
    private String userInfo;

    public void updateEmailAuth(EmailAuth auth) {
        this.emailAuth = auth;
    }

    public void updateMemberInfo(MemberDTO dto) {
        this.userName = dto.getUserName();
        this.pwd = dto.getPwd();
        this.modDate = LocalDateTime.now();
        this.nickname = dto.getNickname();
        this.userInfo = dto.getUserInfo();
    }
}


