package com.example.umc9th.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Getter
    @Builder
    public static class JoinDTO {
        private final Long memberId;
        private final LocalDateTime createAt;
    }
}
