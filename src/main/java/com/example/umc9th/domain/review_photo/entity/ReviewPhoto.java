package com.example.umc9th.domain.review_photo.entity;

import com.example.umc9th.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review_photo")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link", nullable = false, length = 60)
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;
}
