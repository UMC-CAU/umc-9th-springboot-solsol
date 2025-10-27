package com.example.umc9th.domain.member.entity;


import com.example.umc9th.domain.member_food.entity.MemberFood;
import com.example.umc9th.global.entity.BaseEntity;
import com.example.umc9th.domain.member.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "address", length = 40)
    private String address;

    @Column(name = "score")
    private Integer score;

    @Column(name = "state")
    private State state;

    @Column(name = "deactivate_date")
    private LocalDate deactivateDate;

    @Column(name = "role")
    private Role role;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "email", length = 40, unique = true)
    private String email;

    @Column(name = "is_phone_verified")
    private Boolean isPhoneVerified;

    @Column(name = "nickname", length = 10, unique = true)
    private String nickname;
}
