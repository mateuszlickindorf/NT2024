package com.mateusz.library.library.controller.dto.review;
import com.mateusz.library.library.controller.dto.book.GetBookDto;
import com.mateusz.library.library.controller.dto.user.GetUserSimplifiedDto;

import java.sql.Date;

public class GetReviewDto {

    private Long id;

    private GetBookDto book;

    private GetUserSimplifiedDto user;

    private Integer rating;

    private String comment;

    private Date reviewDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GetBookDto getBook() {
        return book;
    }

    public void setBook(GetBookDto book) {
        this.book = book;
    }

    public GetUserSimplifiedDto getUser() {
        return user;
    }

    public void setUser(GetUserSimplifiedDto user) {
        this.user = user;
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

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public GetReviewDto() {
    }

    public GetReviewDto(Long id, GetBookDto book, GetUserSimplifiedDto user, Integer rating, String comment, Date reviewDate) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }
}
