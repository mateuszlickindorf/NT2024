package com.mateusz.library.library.service.review;

import com.mateusz.library.library.controller.dto.book.GetBookDto;
import com.mateusz.library.library.controller.dto.review.*;
import com.mateusz.library.library.controller.dto.user.GetUserSimplifiedDto;
import com.mateusz.library.library.exceptions.BookNotFound;
import com.mateusz.library.library.exceptions.RatingOverScale;
import com.mateusz.library.library.exceptions.ReviewNotFound;
import com.mateusz.library.library.exceptions.UserNotFoundForId;
import com.mateusz.library.library.infrastructure.entity.BookEntity;
import com.mateusz.library.library.infrastructure.entity.ReviewEntity;
import com.mateusz.library.library.infrastructure.entity.UserEntity;
import com.mateusz.library.library.infrastructure.repository.BookRepository;
import com.mateusz.library.library.infrastructure.repository.ReviewRepository;
import com.mateusz.library.library.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<GetReviewDto> getAll(Long bookId, Long userId) {
        List<ReviewEntity> reviews;
        if (bookId != null && userId == null) {
            reviews = reviewRepository.findByBookId(bookId);
        } else if (bookId == null & userId != null) {
            reviews = reviewRepository.findByUserId(userId);
        } else {
            reviews = (List<ReviewEntity>) reviewRepository.findAll();
        }
        return reviews.stream().map(this::mapReview).collect(Collectors.toList());
    }

    public ReviewEntity getById(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found!"));
    }

    @Transactional
    public CreateReviewResponseDto create(CreateReviewDto reviewDTO) {
        BookEntity book = bookRepository.findById(reviewDTO.getBookId()).orElseThrow(() -> BookNotFound.create(reviewDTO.getBookId()));
        UserEntity user = userRepository.findById(reviewDTO.getUserId()).orElseThrow(() -> UserNotFoundForId.create(reviewDTO.getUserId()));
        ReviewEntity review = new ReviewEntity();
        review.setBook(book);
        review.setUser(user);
        if (reviewDTO.getRating() > 10) throw RatingOverScale.create();
        review.setRating(reviewDTO.getRating());
        review.setReviewDate(new Date(System.currentTimeMillis()));
        review.setComment(reviewDTO.getComment());
        var newReview = reviewRepository.save(review);
        return new CreateReviewResponseDto(newReview.getId(), newReview.getBook(), newReview.getUser(), newReview.getRating(), newReview.getComment(), newReview.getReviewDate());
    }

    public void deleteById(long id) {
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("The review doesn't exist in the database!");
        }
        reviewRepository.deleteById(id);
    }

    public EditReviewResponseDto editReview(EditReviewDto reviewDTO) {
        ReviewEntity review = reviewRepository.findById(reviewDTO.getId()).orElseThrow(() -> ReviewNotFound.create(reviewDTO.getId()));
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        review.setReviewDate(new Date(System.currentTimeMillis()));
        reviewRepository.save(review);
        return new EditReviewResponseDto(review.getId(), review.getBook(), review.getUser(), reviewDTO.getRating(), reviewDTO.getComment(), review.getReviewDate());
    }

    private GetReviewDto mapReview(ReviewEntity review) {
        GetBookDto book = new GetBookDto(review.getBook().getId(), review.getBook().getIsbn(), review.getBook().getTitle(), review.getBook().getAuthor(), review.getBook().getPublisher(), review.getBook().getPublication_year(), review.getBook().getAvailableCopies() > 0);
        GetUserSimplifiedDto user = new GetUserSimplifiedDto(review.getUser().getId(), review.getUser().getName(), review.getUser().getEmail());
        return new GetReviewDto(review.getId(), book, user, review.getRating(), review.getComment(), review.getReviewDate());
    }
}