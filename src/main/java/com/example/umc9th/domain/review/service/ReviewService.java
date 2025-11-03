package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.querydsl.core.BooleanBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createReview(Long storeId, String content, Float rate) {
        // 기본 검증
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("리뷰 내용은 필수입니다.");
        }
        if (rate == null || rate < 0f || rate > 5f) {
            throw new IllegalArgumentException("평점은 0부터 5 사이여야 합니다.");
        }

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Review review = Review.builder()
                .content(content)
                .rate(rate)
                .member(member)// member는 현재 로그인 연동 전이므로 1로 저장
                .store(store)
                .build();

        return reviewRepository.save(review).getId();
    }

    @Transactional
    public List<Review> searchReviews(String query, String type) {

        //Q클래스 정의
        QReview review = QReview.review;

        //BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        //BooleanBuilder 사용

        //동적 쿼리: 검색 조건
        if(type.equals("region")) {
            builder.and(review.store.region.name.contains(query));
        }
        if(type.equals("rate")) {
            builder.and(review.rate.goe(Float.parseFloat(query)));
        }
        if(type.equals("both")){

            // & 기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            //firstQuery, secondQuery 로그 출력
            System.out.println("firstQuery: " + firstQuery);
            System.out.println("secondQuery: " + secondQuery);

            //동적 쿼리
            builder.and(review.store.region.name.contains(firstQuery));
            builder.and(review.rate.goe(Float.parseFloat(secondQuery)));



        }
        List<Review> reviewList = reviewRepository.searchReview(builder);
        return reviewList;
    }

}
