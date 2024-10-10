package ru.rsreu.sanitary_ware.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * DTO for {@link ru.rsreu.sanitary_ware.entity.Review}
 */
@Value
public class ReviewDto {
    Long id;
    @NotNull
    Integer rating;
    String comment;
    @NotNull
    Instant reviewDate;
}