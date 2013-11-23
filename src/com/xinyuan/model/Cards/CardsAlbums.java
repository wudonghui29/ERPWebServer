package com.xinyuan.model.Cards;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.BaseModel;

/**
 * 
 * 名片 相冊
 *
 */

@Entity
@Table
public class CardsAlbums extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String albumName ;		// 相冊名称
	private String albumPassword ;	// 相册密码
	
	
	@Column(unique=true)
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public String getAlbumPassword() {
		return albumPassword;
	}
	public void setAlbumPassword(String albumPassword) {
		this.albumPassword = albumPassword;
	}
	
}
