package com.battleq.member.repository;

import com.battleq.member.vo.dto.MemberDTO;
import com.battleq.member.vo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member findMemberByEmail(String email);
    public Optional<Member> findByEmailAndPwd(String email, String pwd);
}
