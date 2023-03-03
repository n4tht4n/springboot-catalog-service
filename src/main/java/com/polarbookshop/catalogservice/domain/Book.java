package com.polarbookshop.catalogservice.domain;

/**
 * Domain model, implemented as a `record` to get an immutable object.
 */
public record Book(
    String isbn,
    String title,
    String author,
    Double price) {
}
