package com.example.umc9th.domain.review.controller;


import com.example.umc9th.domain.review.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryServiceImpl;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Validated
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewQueryServiceImpl reviewQueryService;
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


    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 마크 (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ){
        ReviewResDTO.ReviewPreViewListDTO reviews = reviewQueryService.findReview(storeName, page - 1);
        ReviewSuccessCode code = ReviewSuccessCode.REVIEW_RETRIEVED;
        return ApiResponse.onSuccess(code, reviews);
    }

    @Operation(
            summary = "내가 작성한 리뷰 목록 조회 API",
            description = "특정 회원이 작성한 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/my")
    public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
            @RequestParam Long memberId,
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ){
        ReviewResDTO.MyReviewListDTO reviews = reviewQueryService.findMyReviews(memberId, page - 1);
        ReviewSuccessCode code = ReviewSuccessCode.REVIEW_RETRIEVED;
        return ApiResponse.onSuccess(code, reviews);
    }
}
