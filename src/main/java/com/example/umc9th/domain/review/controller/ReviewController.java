package com.example.umc9th.domain.review.controller;


import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{storeId}")
    public ApiResponse<Long> createReview(@PathVariable Long storeId,
                                          @RequestBody CreateReviewRequest request) {
        Long reviewId = reviewService.createReview(storeId, request.getContent(), request.getRate());
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewId);
    }

    // 요청 본문 DTO (memberId 제거)
    public static class CreateReviewRequest {
        private String content;
        private Float rate;

        public CreateReviewRequest() {}
        public String getContent() { return content; }
        public Float getRate() { return rate; }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ReviewResponse>> getReviews(
            @RequestParam String query,
            @RequestParam String type) {
        List<Review> reviews = reviewService.searchReviews(query, type);
        List<ReviewResponse> body = reviews.stream()
                .map(ReviewResponse::from)
                .toList();
        return ResponseEntity.ok(body);
    }
}
