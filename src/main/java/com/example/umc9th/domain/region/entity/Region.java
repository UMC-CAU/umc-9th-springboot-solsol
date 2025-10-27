package com.example.umc9th.domain.region.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "region")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 20, unique = true)
    private String name;
}
