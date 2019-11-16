package com.books.biblio.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="books")
public class Book implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="TITLE", nullable=false)
	private String titre;

	@Column(name="AUTHOR", nullable=false)
	private String auteur;

	@Column(name="CATEGORY", nullable=false)
	private String category;

	@Column(name="NBR_PG", nullable=false)
	private Integer nbr_page;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public Integer getNbp() {
		return nbr_page;
	}

	public void setNbp(Integer nbr_page) {
		this.nbr_page = nbr_page;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Book book = (Book) o;

		if (Double.compare(book.nbr_page, nbr_page) != 0) return false;
		if (id != null ? !id.equals(book.id) : book.id != null) return false;
		if (titre != null ? !titre.equals(book.titre) : book.titre != null) return false;
		if (auteur != null ? !auteur.equals(book.auteur) : book.auteur != null) return false;
		//if (category != null ? !category.equals(book.category) : book.category != null) return false;
		return category != null ? category.equals(book.category) : book.category == null;


	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id != null ? id.hashCode() : 0;
		result = 31 * result + (titre != null ? titre.hashCode() : 0);
		result = 31 * result + (auteur != null ? auteur.hashCode() : 0);
		result = 31 * result + (category != null ? category.hashCode() : 0);

		result = 31 * result + (nbr_page != null ? nbr_page.hashCode() : 0);


		return result;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", titre=" + titre + ", auteur=" + auteur +", category=" + category
				+ ", nbr_page=" + nbr_page + "]";
	}


}
