package com.mateusz.library.library.infrastructure.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "reviews", schema = "library")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    @JsonManagedReference
    @ManyToOne
    private BookEntity book;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonManagedReference
    @ManyToOne
    private UserEntity user;
    @Basic
    @Column(name = "rating", nullable = false)
    private Integer rating;
    @Basic
    @Column(name = "comment")
    private String comment;
    @Basic
    @Column(name = "review_date")
    private Date reviewDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
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
}
