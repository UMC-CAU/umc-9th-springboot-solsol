package com.example.umc9th.domain.member.enums;

public enum Gender {
    MALE, FEMALE, NONE;
    public static final Gender DEFAULT = NONE;

    public static Gender from(String name) {
        try {
            return Gender.valueOf(name.toUpperCase());
        } catch (Exception e) {
            return DEFAULT; // 잘못된 입력일 경우 기본값 반환
        }
    }
}
