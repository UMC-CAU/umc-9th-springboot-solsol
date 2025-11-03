package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.review.entity.Review;

public record ReviewResponse(
        Long id,
        String content,
        Float rate,
        Long memberId,
        Long storeId
) {
    public static ReviewResponse from(Review r) {
        return new ReviewResponse(
                r.getId(),
                r.getContent(),
                r.getRate(),
                r.getMember() != null ? r.getMember().getId() : null,
                r.getStore() != null ? r.getStore().getId() : null
        );
    }
}
