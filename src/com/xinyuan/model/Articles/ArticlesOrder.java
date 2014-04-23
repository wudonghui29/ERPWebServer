package com.xinyuan.model.Articles;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class ArticlesOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String title;
	
	private String articles;
	

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(columnDefinition="TEXT")
	public String getArticles() {
		return articles;
	}

	public void setArticles(String articles) {
		this.articles = articles;
	}
	
	
	
}
