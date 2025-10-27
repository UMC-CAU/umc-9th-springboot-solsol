package com.example.umc9th.domain.member.enums;

public enum Role {
    //사장님 영어로
    USER, ADMIN, OWNER;
    public static final Role DEFAULT = USER;
    public static Role from(String name) {
        try {
            return Role.valueOf(name.toUpperCase());
        } catch (Exception e) {
            return DEFAULT; // 잘못된 입력일 경우 기본값 반환
        }
    }
}
