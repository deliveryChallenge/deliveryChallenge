package com.challnege.delivery.review.dto;

import lombok.Getter;

@Getter
public class ReviewRequestDto {
    private Long customerId;
    private String nickname;
    private String content;
    private int rating;
}
