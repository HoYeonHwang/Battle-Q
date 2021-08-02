package com.battleq.member.controller;

import com.battleq.member.domain.dto.MemberDto;
import com.battleq.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class MemberController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MemberService memberService;
    /**
     *  회원가입
     */
    @PostMapping("/member/regist")
    public ResponseEntity<String> registMember(@RequestBody MemberDto dto) throws Exception {
        String registEmail = memberService.registMember(dto);
        return new ResponseEntity<String>(registEmail,HttpStatus.CREATED);
    }
    /**
     * 가입 가능한 E-MAIL 확인
     */
    @GetMapping("/member/validate/email/{email}")
    public ResponseEntity validateAvailableEmail(@PathVariable("email") String email) throws Exception {
        Boolean isAvailableEmail = memberService.validateAvailableEmail(email);
        if (isAvailableEmail) {
            return new ResponseEntity(HttpStatus.OK); // 200
        }
        else {
            return new ResponseEntity(HttpStatus.CONFLICT); // 409
        }
    }

    /**
     * 가입 가능한 닉네임 확인
     */
    @GetMapping("/member/validate/nickname/{nickName}")
    public ResponseEntity validateAvailableNickName(@PathVariable("nickName") String nickName) throws Exception {
        Boolean isAvailableNickName = memberService.validateAvailableNickName(nickName);
        if (isAvailableNickName) {
            return new ResponseEntity(HttpStatus.OK); // 200
        }
        else {
            return new ResponseEntity(HttpStatus.CONFLICT); // 409
        }
    }

    /**
     * 회원정보 수정
     */
    @PutMapping("/member/modify")
    public ResponseEntity<String> modifyMemberInfo(@RequestBody MemberDto dto) throws Exception {
        String email = memberService.modifyMemberInfo(dto);
        return new ResponseEntity<String>(email,HttpStatus.OK);
    }
    /**
     * 로그인
     */
    @PostMapping("/member/login")
    public ResponseEntity<String> doLogin(@RequestBody MemberDto dto) throws Exception {
        String token = memberService.validateLogin(dto);
        return new ResponseEntity<String>(token,HttpStatus.OK);
    }
    /**
     * 유저 상세보기
     */
    @GetMapping("/member/detail/{email}")
    public ResponseEntity<MemberDto> getMemberDetail(@PathVariable("email")String email) throws Exception {
        MemberDto memberDetail = memberService.getMemberDetail(email);
        return new ResponseEntity<MemberDto>(memberDetail,HttpStatus.OK);
    }
}
