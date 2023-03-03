package com.polarbookshop.catalogservice.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

import com.polarbookshop.catalogservice.domain.Book;

/**
 * Spring Boot automatically configures the Jackson lib to (de)serialize JSON
 * objects. Using `@JsonTest`, you can test this for your domain objects.
 *
 * The `JacksonTester` utility is configured to work with the JsonPath
 * and JSONAssert libs to help checking the JSON mappings.
 */
@JsonTest
public class BookJsonTests {
  @Autowired
  private JacksonTester<Book> json;

  @Test
  void testSerialize() throws Exception {
    var book = new Book("1234567890", "Title", "Author", 9.90);
    var jsonContent = this.json.write(book);

    assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
        .isEqualTo(book.isbn());
    assertThat(jsonContent).extractingJsonPathStringValue("@.title")
        .isEqualTo(book.title());
    assertThat(jsonContent).extractingJsonPathStringValue("@.author")
        .isEqualTo(book.author());
    assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
        .isEqualTo(book.price());
  }

  @Test
  void testDeserialize() throws Exception {
    var content = """
        {
          "isbn": "1234567890",
          "title": "Title",
          "author": "Author",
          "price": 9.90
        }
        """;
    assertThat(this.json.parse(content)).usingRecursiveComparison()
        .isEqualTo(new Book("1234567890", "Title", "Author", 9.90));
  }
}
