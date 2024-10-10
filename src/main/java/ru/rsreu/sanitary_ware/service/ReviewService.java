package ru.rsreu.sanitary_ware.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rsreu.sanitary_ware.repository.ReviewRepository;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
}
