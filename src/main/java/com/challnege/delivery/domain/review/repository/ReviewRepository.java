package com.challnege.delivery.domain.review.repository;

import com.challnege.delivery.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
