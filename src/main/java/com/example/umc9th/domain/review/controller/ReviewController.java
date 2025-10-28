package com.example.umc9th.domain.review.controller;


import com.example.umc9th.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/{storeId}")
    public ResponseEntity<Long> createReview(@PathVariable Long storeId,
                                             @RequestBody CreateReviewRequest request) {
        Long reviewId = reviewService.createReview(storeId, request.getContent(), request.getRate());
        return ResponseEntity.ok(reviewId);
    }

    // 요청 본문 DTO (memberId 제거)
    public static class CreateReviewRequest {
        private String content;
        private Float rate;

        public CreateReviewRequest() {}
        public String getContent() { return content; }
        public Float getRate() { return rate; }
    }
}
