package com.polarbookshop.catalogservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

import com.polarbookshop.catalogservice.domain.Book;

/**
 * Integration tests covering interactions between Spring components require
 * an application context.
 *
 * The `@SpringBootTest` annotation makes sure such an application context
 * is available when running the integration tests. If not otherwise configured,
 * the class annotated with `@SpringBootApplication` will get the configuration
 * source for component scanning and properties.
 *
 * When working with web apps, tests can be run against a mocked web environment
 * or a running server. Use the `webEnvironment` attribute with
 * `@SpringBootTest` to configure this.
 *
 * With the mocked web environment, you can use the `MockMvc` object to send
 * HTTP requests and check their results. When running the server, you could
 * use `TestRestTemplate` for assertions.
 *
 * But the way to go nowadays is using `WebTestClient` for both scenarios. It
 * provides a more modern and fluent API and additonal features. E.g. you can
 * use it for both, imperative and reactive applications. However, as it is part
 * of the Spring WebFlux project, you'll need an additional dependency for
 * testing: `spring-boot-starter-webflux`!
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {

  /**
   * Autowiring like this is discouraged in Spring applications, but it's ok
   * for testing purposes... it's easier like this.
   */
  @Autowired
  private WebTestClient webTestClient;

  @Test
  void whenPostRequestThenBookCreated() {
    var expectedBook = new Book("1231231231", "Title", "Author", 9.90);
    this.webTestClient
        .post()
        .uri("/books")
        .bodyValue(expectedBook)
        .exchange()
        .expectStatus().isCreated()
        .expectBody(Book.class).value(actualBook -> {
          assertThat(actualBook).isNotNull();
          assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
        });
  }
}
