package com.emnarkx.FirstSpring.review.impl;

import com.emnarkx.FirstSpring.company.Company;
import com.emnarkx.FirstSpring.company.CompanyService;
import com.emnarkx.FirstSpring.review.Review;
import com.emnarkx.FirstSpring.review.ReviewRepository;
import com.emnarkx.FirstSpring.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
//        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
//        return null;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviewList = reviewRepository.findByCompanyId(companyId);
        return reviewList.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review UpdatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            review.setTitle(UpdatedReview.getTitle());
            review.setDescription(UpdatedReview.getDescription());
            review.setRating(UpdatedReview.getRating());
            review.setCompany(companyService.getCompanyById(companyId));
            reviewRepository.save(review);
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if (companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
            // still need to update this method following the video
            return true;
        }
        return false;
    }
}
