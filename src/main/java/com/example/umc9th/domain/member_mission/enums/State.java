package com.example.umc9th.domain.member_mission.enums;

public enum State {
    ONGOING,
    COMPLETED,
    FAILED;
    public static final State DEFAULT = ONGOING;
    public static State from(String name) {
        try {
            return State.valueOf(name.toUpperCase());
        } catch (Exception e) {
            return DEFAULT; // 잘못된 입력일 경우 기본값 반환
        }
    }
}
