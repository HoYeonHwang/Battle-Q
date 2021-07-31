package com.battleq.member.service;

import com.battleq.config.jwt.JwtTokenProvider;
import com.battleq.member.domain.dto.MemberDto;
import com.battleq.member.domain.entity.Authority;
import com.battleq.member.domain.entity.EmailAuth;
import com.battleq.member.domain.entity.Member;
import com.battleq.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원가입
     */
    public String registMember(MemberDto dto) throws Exception {
        dto.updateEmailAuth(EmailAuth.N);
        dto.updateAuthority(Authority.ROLE_STUDENT);
        String encodePassword = passwordEncoder.encode(dto.getPwd());
        dto.updateEncodePassword(encodePassword);
        Member member = dto.toEntity();
        return memberRepository.save(member).getEmail();
    }

    /**
     * 가입 가능한 email 검증
     */
    public Boolean validateAvailableEmail(String email) throws Exception {
        Optional<Member> member = memberRepository.findMemberByEmail(email);
        if (member.isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 가입 가능한 nickname 검증
     */
    public Boolean validateAvailableNickName(String nickName) throws Exception {
        int nickNameCount = memberRepository.countMemberByNickname(nickName);
        return nickNameCount > 0 ? false : true;
    }

    /**
     * 회원 정보 수정
     */
    public String modifyMemberInfo(MemberDto dto) throws Exception {
        Member member = memberRepository.findMemberByEmail(dto.getEmail())
                .orElseThrow(() -> new Exception("해당 유저가 존재하지 않습니다."));
        member.updateMemberInfo(dto);
        return memberRepository.save(member).getEmail();
    }
    /**
     * 로그인
     */
    public String validateLogin(MemberDto dto) throws Exception {
        Member member = memberRepository.findMemberByEmail(dto.getEmail())
                .orElseThrow(() -> new Exception("해당 e-mail이 존재하지 않습니다."));
        if (!passwordEncoder.matches(dto.getPwd(), member.getPwd())) {
            throw new Exception("비밀번호가 다릅니다.");
        }
        return jwtTokenProvider.createToken(member.getEmail(),Arrays.asList(member.getConvertAuthority()));
    }

    /**
     *  유저 상세 보기
     */
    public MemberDto getMemberDetail(String email) throws Exception {
        Member member = memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> new Exception("해당 유저가 존재하지 않습니다."));
        return MemberDto.builder()
                .userName(member.getUserName())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .userInfo(member.getUserInfo())
                .authority(member.getAuthority())
                .build();
    }
}
