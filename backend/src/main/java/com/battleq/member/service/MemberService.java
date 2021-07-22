package com.battleq.member.service;

import com.battleq.member.repository.MemberRepository;
import com.battleq.member.vo.dto.MemberDTO;
import com.battleq.member.vo.entity.EmailAuth;
import com.battleq.member.vo.entity.Member;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService{

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원가입
     */
    public String registMember(MemberDTO dto) throws Exception {
        dto.updateEmailAuth(EmailAuth.N);
        String encodePassword = bCryptPasswordEncoder.encode(dto.getEmail());
        dto.updateEncodePassword(encodePassword);
        Member member = dto.toEntity();
        return memberRepository.save(member).getEmail();
    }

    /**
     *  회원 가능한 email 검증
     */
    public Boolean validateAvailableEmail(String email) throws Exception {
        Member member = memberRepository.findMemberByEmail(email);
        return member == null ? true : false;
    }

    /**
     * 회원 정보 수정
     */

    public String modifyMemberInfo(MemberDTO dto) throws Exception {
        dto.updateEncodePassword(bCryptPasswordEncoder.encode(dto.getPwd()));
        Member member = memberRepository.findByEmailAndPwd(dto.getEmail(),dto.getPwd())
                .orElseThrow(()->new Exception("해당 E-MAIL 유저가 존재하지 않습니다."));
        member.updateMemberInfo(dto);
        return memberRepository.save(member).getEmail();
    }
}
