package com.polarbookshop.catalogservice.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

/**
 * Domain model, implemented as a `record` to get an immutable object.
 *
 * Adds some Java Bean Validation annotations to make the object validatable
 * using the `spring-boot-start-validation` dependency. To enable the
 * validation, put `@Valid` before any received JSON object
 * in the `BookController`...
 */
public record Book(
    @NotBlank(message = "The book ISBN must be defined.") //
    @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "The ISBN format must be valid.") //
    String isbn,

    @NotBlank(message = "The book title must be defined.") //
    String title,

    @NotBlank(message = "The book author must be defined.") //
    String author,

    @NotNull(message = "The book price must be defined.") //
    @Positive(message = "The book price must be greater than zero.") //
    Double price) {
}
