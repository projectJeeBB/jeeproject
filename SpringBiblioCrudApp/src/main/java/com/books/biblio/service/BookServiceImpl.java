package com.books.biblio.service;

import java.util.List;

import com.books.biblio.model.Book;
import com.books.biblio.repositories.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("userService")
@Transactional
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;

	public Book findById(Long id) {
		return bookRepository.findOne(id);
	}

	public Book findByTitre(String name) {
		return bookRepository.findByTitre(name);
	}

	public void saveBook(Book book) {
		bookRepository.save(book);
	}

	public void updateBook(Book book){
		saveBook(book);
	}

	public void deleteBookById(Long id){
		bookRepository.delete(id);
	}

	public void deleteAllBooks(){
		bookRepository.deleteAll();
	}

	public List<Book> findAllBooks(){
		return bookRepository.findAll();
	}

	public boolean isBookExist(Book book) {
		return findByTitre(book.getTitre()) != null;
	}

	

	}


