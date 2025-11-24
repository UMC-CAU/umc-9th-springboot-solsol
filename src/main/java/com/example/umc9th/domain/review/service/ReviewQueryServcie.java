package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryServcie {

    public ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page);
    ReviewResDTO.MyReviewListDTO findMyReviews(Long memberId, Integer page);
}
