package com.polarbookshop.catalogservice.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;

/**
 * Annotating this class with `@ConditionalOnProperty` loads and activates this component only
 * when the specified `polar.testdata.enabled` property is available and set to `true`.
 *
 * So you're quite flexible when you would need this component in your application.
 * You can put this property into a profile-specific property file and activate
 * this profile when needed, like running locally or in a dev or staging environment.
 */
@Component
@ConditionalOnProperty(name = "polar.testdata.enabled", havingValue = "true")
public class BookDataLoader {
	private final BookRepository bookRepository;

	public BookDataLoader(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	/**
   * Using this `@EventListener`, this method is triggered by Spring, when the
	 * `ApplicationReadyEvent` is received. This means the application finished
	 * the startup and the repository is ready to save data.
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void loadBookTestData() {
		var book1 = new Book("1234567891", "Northern Lights", "Lyra Silverstar", 9.90);
		var book2 = new Book("1234567892", "Polar Journey", "Iorek Polarson", 12.90);

		this.bookRepository.save(book1);
		this.bookRepository.save(book2);
	}
}
