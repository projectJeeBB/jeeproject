package com.books.biblio.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.books.biblio.model.Book;
import com.books.biblio.service.BookService;
import com.books.biblio.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	BookService bookService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Books---------------------------------------------

	@RequestMapping(value = "/book/", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> listAllBooks() {
		List<Book> books = bookService.findAllBooks();
		if (books.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	// -------------------Retrieve Single book------------------------------------------

	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBook(@PathVariable("id") long id) {
		logger.info("Fetching Book with id {}", id);
		Book book = bookService.findById(id);
		if (book == null) {
			logger.error("book with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("book with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	// -------------------Create a book-------------------------------------------

	@RequestMapping(value = "/book/", method = RequestMethod.POST)
	public ResponseEntity<?> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {
		logger.info("Creating book : {}", book);

		if (bookService.isBookExist(book)) {
			logger.error("Unable to create. A book with name {} already exist", book.getTitre());
			return new ResponseEntity(new CustomErrorType("Unable to create. A book with name " + 
			book.getTitre() + " already exist."),HttpStatus.CONFLICT);
		}
		bookService.saveBook(book);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/book/{id}").buildAndExpand(book.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a book ------------------------------------------------

	@RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
		logger.info("Updating book with id {}", id);

		Book currentBook = bookService.findById(id);

		if (currentBook == null) {
			logger.error("Unable to update. book with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. book with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentBook.setTitre(book.getTitre());
		currentBook.setAuteur(book.getAuteur());
		currentBook.setCategory(book.getCategory());
		currentBook.setNbp(book.getNbp());




		bookService.updateBook(currentBook);
		return new ResponseEntity<Book>(currentBook, HttpStatus.OK);
	}

	// ------------------- Delete a book-----------------------------------------

	@RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBook(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting book with id {}", id);

		Book book = bookService.findById(id);
		if (book == null) {
			logger.error("Unable to delete. book with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. book with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		bookService.deleteBookById(id);
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All book-----------------------------

	@RequestMapping(value = "/book/", method = RequestMethod.DELETE)
	public ResponseEntity<Book> deleteAllBooks() {
		logger.info("Deleting All Books");

		bookService.deleteAllBooks();
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}

}