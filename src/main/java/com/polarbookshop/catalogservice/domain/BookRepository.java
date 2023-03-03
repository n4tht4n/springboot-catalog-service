package com.polarbookshop.catalogservice.domain;

import java.util.Optional;

/**
 * Persistence layer interface for the `BookService` to decouple the abstraction
 * from the actual implementation.
 *
 * This repository interface belongs to the domain layer, it's implementation(s)
 * are part of the persistence layer.
 */
public interface BookRepository {
  Iterable<Book> findAll();

  Optional<Book> findByIsbn(String isbn);

  boolean existsByIsbn(String isbn);

  Book save(Book book);

  void deleteByIsbn(String isbn);
}
