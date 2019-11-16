package com.books.biblio.service;


import java.util.List;

import com.books.biblio.model.Book;

public interface BookService {
	
	Book findById(Long id);

	Book findByTitre(String titre);

	void saveBook(Book book);

	void updateBook(Book book);

	void deleteBookById(Long id);

	void deleteAllBooks();

	List<Book> findAllBooks();

	boolean isBookExist(Book book);
}