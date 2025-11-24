package com.example.umc9th.domain.member_mission.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberMissionSuccessCode implements BaseSuccessCode {
    MEMBER_MISSION_RETRIEVED(HttpStatus.OK, "MEMBER_MISSION200_1", "회원 미션이 성공적으로 조회되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

