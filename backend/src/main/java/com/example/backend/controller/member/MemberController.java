package com.example.backend.controller.member;

import com.example.backend.dto.member.Member;
import com.example.backend.dto.member.MemberEdit;
import com.example.backend.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    final MemberService service;

    @PutMapping("update")
    public ResponseEntity<Map<String, Object>> update(@RequestBody MemberEdit member) {
        if (service.update(member)) {
            //잘됨
            return ResponseEntity.ok(Map.of("message",
                    Map.of("type", "success",
                            "text", "회원정보를 수정하였습니다.")));
        } else {
            return ResponseEntity.badRequest()
                    .body(Map.of("message",
                            Map.of("type", "warning",
                                    "text", "정확한 정보를 입력해주세요.")));
        }


    }

    @DeleteMapping("remove")
    public ResponseEntity<Map<String, Object>> remove(@RequestBody Member member) {
        if (service.remove(member)) {
            //잘됨
            return ResponseEntity.ok(Map.of("message",
                    Map.of("type", "success",
                            "text", "회원정보를 삭제하였습니다.")));
        } else {
            return ResponseEntity.badRequest()
                    .body(Map.of("message",
                            Map.of("type", "warning",
                                    "text", "정확한 정보를 입력해주세요.")));
        }
    }

    @GetMapping("{id}")
    public Member getMember(@PathVariable String id) {

        return service.get(id);
    }


    @GetMapping("list")
    public List<Member> list() {
        return service.list();
    }

    @GetMapping(value = "check", params = "email")
    public ResponseEntity<Map<String, Object>> checkEmail(@RequestParam String email) {
        if (service.checkEmail(email)) {
            // 이미 있으면
            return ResponseEntity.ok().body(Map.of(
                    "message", Map.of("type", "warning", "text", "이미 사용중인 이메일 입니다."),
                    "available", false)
            );
        } else {
            // 없으면
            return ResponseEntity.ok().body(Map.of(
                    "message", Map.of("type", "info", "text", "사용 가능한 이메일 입니다."),
                    "available", true));
        }
    }

    @GetMapping(value = "check", params = "id")
    public ResponseEntity<Map<String, Object>> checkId(@RequestParam String id) {
        if (service.checkId(id)) {
            // 이미 있으면
            return ResponseEntity.ok().body(Map.of(
                    "message", Map.of("type", "warning", "text", "이미 사용중인 아이디 입니다."),
                    "available", false)
            );
        } else {
            // 없으면
            return ResponseEntity.ok().body(Map.of(
                    "message", Map.of("type", "info", "text", "사용 가능한 아이디 입니다."),
                    "available", true));
        }
    }

    @PostMapping("signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody Member member) {
        try {

            if (service.add(member)) {
                return ResponseEntity.ok().body(Map.of("message",
                        Map.of("type", "success", "text", "회원 가입되었습니다.")));
            } else {
                return ResponseEntity.internalServerError().body(Map.of("message",
                        Map.of("type", "error", "text", "회원 가입 중 문제가 발생하였습니다.")));
            }
        } catch (DuplicateKeyException e) {
            return ResponseEntity.internalServerError().body(Map.of("message",
                    Map.of("type", "error", "text", "이미 존재하는 아이디입니다.")));
        }
    }

}