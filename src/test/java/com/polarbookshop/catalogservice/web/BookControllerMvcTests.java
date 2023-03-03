package com.polarbookshop.catalogservice.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.polarbookshop.catalogservice.domain.BookNotFoundException;
import com.polarbookshop.catalogservice.domain.BookService;

/**
 * Some integration tests (like this one) might not need the full-blown app
 * context! E.g. there's no need to load the web components when you're testing
 * the persistence layer, and vice versa...
 *
 * Spring Boot allows you to use contexts initialized only with a subgroup of
 * components/beans, targeting a specific app slice. Those slice tests don't use
 * the `@SpringBootTest` annotation, but one of a set of annotations dedicated
 * to particular parts of an app: Web MVC, Web Flux, REST client, JDBC, JPA,
 * Mongo, Redis, JSON, and more. Each of those annotations initializes an app
 * context, filtering out all the other beans outside of that slice.
 *
 * This test will focus on the Spring MVC `BookController` and for this, the
 * `@WebMvcTest` is the right annotation. It includes `@RestController` and
 * `@RestControllerAdvice`.
 *
 * Additionally, it's a good idea to even narrow down the test by directly
 * providing the controller under test.
 */
@WebMvcTest(BookController.class)
public class BookControllerMvcTests {
  @Autowired
  private MockMvc mockMvc;

  /**
   * This will add a mocked `BookService`...
   *
   * Mocks created with `@MockBean` are also included in the app context. So
   * whenever the context is aksed to autowire that bean, it injects the mock
   * rather than the actual implemetation.
   */
  @MockBean
  private BookService bookService;

  @Test
  void whenGetBookNotExistingThenShouldReturn404() throws Exception {
    String isbn = "73737313940";
    given(this.bookService.viewBookDetails(isbn))
        .willThrow(BookNotFoundException.class);
    this.mockMvc.perform(get("/books/" + isbn)).andExpect(status().isNotFound());
  }
}
