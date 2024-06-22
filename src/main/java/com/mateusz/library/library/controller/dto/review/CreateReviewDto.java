package com.mateusz.library.library.controller.dto.review;

import jakarta.validation.constraints.NotNull;

public class CreateReviewDto {
    @NotNull
    private Long bookId;
    @NotNull
    private Long userId;
    @NotNull
    private Integer rating;
    private String comment;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CreateReviewDto() {
    }

    public CreateReviewDto(Long bookId, Long userId, Integer rating, String comment) {
        this.bookId = bookId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }
}
