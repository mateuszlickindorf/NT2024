package com.mateusz.library.library.controller.review;
import com.mateusz.library.library.controller.dto.review.*;
import com.mateusz.library.library.infrastructure.entity.ReviewEntity;
import com.mateusz.library.library.service.review.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@PreAuthorize("hasAnyRole('ADMIN', 'READER')")
@Tag(name = "Review")
@CrossOrigin
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'READER')")
    @ResponseStatus(code= HttpStatus.CREATED)
    public ResponseEntity<CreateReviewResponseDto> add(@RequestBody CreateReviewDto reviewDto) {
        var newReview = reviewService.create(reviewDto);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @PatchMapping("/edit")
    @PreAuthorize("hasAnyRole('ADMIN', 'READER')")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<EditReviewResponseDto> edit(@RequestBody EditReviewDto reviewDto) {
        var editedReview = reviewService.editReview(reviewDto);
        return new ResponseEntity<>(editedReview, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'READER')")
    public ReviewEntity getById(@PathVariable long id) {
        return reviewService.getById(id);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('ADMIN', 'READER')")
    public @ResponseBody ResponseEntity<List<GetReviewDto>> getAll(@RequestParam(required = false) Long bookId, @RequestParam(required = false) Long userId) {
        List<GetReviewDto> getReviewDto = reviewService.getAll(bookId, userId);
        return new ResponseEntity<>(getReviewDto, HttpStatus.OK);
    }
}
