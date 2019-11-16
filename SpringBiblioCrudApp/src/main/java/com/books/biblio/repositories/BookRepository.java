package com.books.biblio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.books.biblio.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitre(String titre);

}
