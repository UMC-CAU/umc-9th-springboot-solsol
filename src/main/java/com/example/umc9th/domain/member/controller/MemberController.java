package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.exception.code.MemberSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.service.MemberService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ApiResponse<Member> getMember(@PathVariable Long memberId) {
        Member member = memberService.getMemberById(memberId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, member);
    }

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(@RequestBody MemberReqDTO.JoinDTO dto) {
        MemberResDTO.JoinDTO res = memberService.signUp(dto);
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, res);
    }
}
