package com.nhnacademy.springboot.accountapi.controller;

import com.nhnacademy.springboot.accountapi.domain.Member;
import com.nhnacademy.springboot.accountapi.request.LoginRequest;
import com.nhnacademy.springboot.accountapi.service.MemberService;
import com.nhnacademy.springboot.accountapi.service.impl.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
@Slf4j
public class AccountRestController {
    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers(HttpServletRequest request) {
        String memberId = request.getHeader("MEMBER-SERIAL-ID"); // 헤더 추출 "MEMBER-SERIAL-ID"
        List<Member> members = memberService.getAllMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable("id") Long id, HttpServletRequest request) {
        String memberId = request.getHeader("MEMBER-SERIAL-ID");
        Member member = memberService.getMemberById(id);
        if (member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/members")
    public ResponseEntity<Member> createMember(@RequestBody Member member, HttpServletRequest request) {
        String memberId = request.getHeader("MEMBER-SERIAL-ID");
        Member createdMember = memberService.createMember(member);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id, HttpServletRequest request) {
        String memberId = request.getHeader("MEMBER-SERIAL-ID");
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 특정 멤버 상태 변경하는 엔트포인트
    @PutMapping("/members/{id}/status")
    public ResponseEntity<Void> updateMemberStatus(
            @PathVariable("id") Long memberId,
            @RequestParam("statusId") int statusId,
            HttpServletRequest request) throws MemberNotFoundException {
        String memberIdFromHeader = request.getHeader("MEMBER-SERIAL-ID");

        if (memberIdFromHeader == null || !memberIdFromHeader.equals(memberId.toString())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // header 정보와 다르면 응답 없음 응답 반환 부분
        }
        // 멤버 상태 변경
        memberService.updateMemberStatus(memberId,statusId);
        // 상태 변경 성공 했으면 응답
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Member> loginCheck(@RequestBody LoginRequest loginRequest) {
        String memberId = loginRequest.getMemberId();
        String password = loginRequest.getPassword();

        Optional<Member> optionalMember = memberService.findMemberByMemberIdAndPassword(memberId, password);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            return new ResponseEntity<>(member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
