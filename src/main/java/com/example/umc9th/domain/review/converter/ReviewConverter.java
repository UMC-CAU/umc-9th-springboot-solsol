// filepath: src/main/java/com/example/umc9th/domain/review/converter/ReviewConverter.java
package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewConverter {

    // Page<Review> -> ReviewPreViewListDTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(Page<Review> reviewPage) {

        List<ReviewResDTO.ReviewPreViewDTO> reviewList = reviewPage.getContent().stream()
                .map(ReviewConverter::toReviewPreviewDTO)
                .collect(Collectors.toList());

        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }

    // Review -> ReviewPreViewDTO
    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(Review review) {

        // createdAt이 null 일 수도 있으니 방어적으로 처리
        LocalDate createdDate = review.getCreatedAt() != null
                ? review.getCreatedAt().toLocalDate()
                : null;

        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getNickname())  // Member에 nickname 필드 있다고 가정
                .score(review.getRate())
                .body(review.getContent())
                .createdAt(createdDate)
                .build();
    }

    // Page<Review> -> MyReviewListDTO
    public static ReviewResDTO.MyReviewListDTO toMyReviewListDTO(Page<Review> reviewPage) {
        List<ReviewResDTO.MyReviewDTO> reviewList = reviewPage.getContent().stream()
                .map(ReviewConverter::toMyReviewDTO)
                .collect(Collectors.toList());

        return ReviewResDTO.MyReviewListDTO.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }

    // Review -> MyReviewDTO
    public static ReviewResDTO.MyReviewDTO toMyReviewDTO(Review review) {
        LocalDate createdDate = review.getCreatedAt() != null
                ? review.getCreatedAt().toLocalDate()
                : null;

        return ReviewResDTO.MyReviewDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .score(review.getRate())
                .body(review.getContent())
                .createdAt(createdDate)
                .build();
    }
}
