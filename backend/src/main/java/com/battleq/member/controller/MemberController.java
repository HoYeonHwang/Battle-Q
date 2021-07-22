package com.battleq.member.controller;

import com.battleq.member.service.MemberService;
import com.battleq.member.vo.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class MemberController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MemberService memberService;
    /**
     *  회원가입
     */
    @PostMapping("/regist")
    public ResponseEntity<String> registMember(@RequestBody MemberDTO dto) throws Exception {
        log.info("======regist Member=========");
        log.info("======dto=========");
        log.info(dto.toString());
        String registEmail = memberService.registMember(dto);
        return new ResponseEntity<String>(registEmail,HttpStatus.CREATED);
    }
    /**
     * 가입 가능한 E-MAIL 확인
     */
    @GetMapping("/validate/email/")
    public ResponseEntity validateAvailableEmail(@RequestBody String email) throws Exception {
        Boolean isAvailableEmail = memberService.validateAvailableEmail(email);
        ResponseEntity result = null;
        if (isAvailableEmail) {
            result = new ResponseEntity(HttpStatus.OK); // 200
        }
        else {
            result = new ResponseEntity(HttpStatus.CONFLICT); // 409
        }
        return result;
    }
    /**
     * 회원정보 수정
     */
    @PutMapping("/member")
    public ResponseEntity<String> modifyMemberInfo(@RequestBody MemberDTO dto) throws Exception {
        String email = memberService.modifyMemberInfo(dto);
        return new ResponseEntity<String>(email,HttpStatus.OK);
    }
}
