package com.polarbookshop.catalogservice.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookService;

/**
 * Exposes the use cases through a REST API.
 *
 * The `@RestController` annotation provided by Spring MVC allows the defintion
 * of methods to handle incoming HTTP requests for specific HTTP method and
 * resource endpoints.
 *
 * `@RequestMapping("books")` tells Spring, that the root path mapping URI for
 * this controller provides only handlers for `/books` (and anything below).
 *
 * The specific handler will use specific HTTP method related mappings, e.g.
 * `@GetMapping` or `@PostMapping`. All those mappings can specify a sub URI
 * below the root path mapping. If not, they will work directly on the URI of
 * the root path!
 *
 * If a mapping needs a sub path, URI template variables (values inside curly
 * braces, like `"{isbn}"`) are used when declaring the mapping.
 * `@PathVariable` then binds the corresponding method parameter to the
 * matching URI template variable. To receive a "full-blown" JSON object, use
 * `@RequestBody`.
 *
 * If a specific HTTP response status is required, use the `@ResponseStatus`
 * annotation.
 */
@RestController
@RequestMapping("books")
public class BookController {
  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  /**
   * Gets all available books.
   */
  @GetMapping
  public Iterable<Book> get() {
    return this.bookService.viewBookList();
  }

  /**
   * Gets a single book identified by its ISBN.
   */
  @GetMapping("{isbn}")
  public Book getByIsbn(@PathVariable String isbn) {
    return this.bookService.viewBookDetails(isbn);
  }

  /**
   * Saves the given book.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Book post(@Valid @RequestBody Book book) {
    return this.bookService.addBookToCatalog(book);
  }

  @DeleteMapping("{isbn}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String isbn) {
    this.bookService.removeBookFromCatalog(isbn);
  }

  @PutMapping("{isbn}")
  public Book put(@PathVariable String isbn, @Valid @RequestBody Book book) {
    return this.bookService.editBookDetails(isbn, book);
  }
}
