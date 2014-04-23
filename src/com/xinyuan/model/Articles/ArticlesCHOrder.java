package com.xinyuan.model.Articles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;

/**
 * 章程异动单
 *
 */

@Entity
@Table
public class ArticlesCHOrder extends OrderApp4 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
    private String title_O;
	private String articles_O;
	
	
	private String title_N;
	private String articles_N;
	
	
	public String getTitle_O() {
		return title_O;
	}
	public void setTitle_O(String title_O) {
		this.title_O = title_O;
	}
	
	@Column(columnDefinition="TEXT")
	public String getArticles_O() {
		return articles_O;
	}
	public void setArticles_O(String articles_O) {
		this.articles_O = articles_O;
	}
	public String getTitle_N() {
		return title_N;
	}
	public void setTitle_N(String title_N) {
		this.title_N = title_N;
	}
	
	@Column(columnDefinition="TEXT")
	public String getArticles_N() {
		return articles_N;
	}
	public void setArticles_N(String articles_N) {
		this.articles_N = articles_N;
	}
	
	
	

}
