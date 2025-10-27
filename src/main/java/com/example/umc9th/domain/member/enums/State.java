package com.example.umc9th.domain.member.enums;

public enum State {
    ACTIVE,
    INACTIVE,
    SUSPENDED;
    public static final State DEFAULT = ACTIVE;
    public static State from(String name) {
        try {
            return State.valueOf(name.toUpperCase());
        } catch (Exception e) {
            return DEFAULT; // 잘못된 입력일 경우 기본값 반환
        }
    }
}
