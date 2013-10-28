package com.xinyuan.model.Cards;

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
	private String albumName ;		// 相冊名称
	private String albumPassword ;	// 相册密码
}
