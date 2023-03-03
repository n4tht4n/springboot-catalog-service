package com.polarbookshop.catalogservice.domain;

import org.springframework.stereotype.Service;

/**
 * Service to implement the use cases for interacting with a `Book`.
 *
 * The `@Service` annotation marks this class to be managed by Spring.
 */
@Service
public class BookService {
  /**
   * Access to the persistence layer, provided through constructor autowiring.
   */
  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Iterable<Book> viewBookList() {
    return this.bookRepository.findAll();
  }

  public Book viewBookDetails(String isbn) {
    return this.bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
  }

  public Book addBookToCatalog(Book book) {
    if (this.bookRepository.existsByIsbn(book.isbn())) {
      throw new BookAlreadyExistsException(book.isbn());
    }
    return this.bookRepository.save(book);
  }

  public void removeBookFromCatalog(String isbn) {
    this.bookRepository.deleteByIsbn(isbn);
  }

  public Book editBookDetails(String isbn, Book book) {
    return this.bookRepository.findByIsbn(isbn)
        .map(existingBook -> {
          var bookToUpdate = new Book(
              existingBook.isbn(),
              book.title(),
              book.author(),
              book.price());
          return this.bookRepository.save(bookToUpdate);
        })
        .orElseGet(() -> addBookToCatalog(book));
  }
}
