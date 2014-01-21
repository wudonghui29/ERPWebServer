package com.xinyuan.model.SharedOrder;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp3;

@Entity
@Table
public class SharedEventReportOrder extends OrderApp3 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String proxyEmployeeNO;			//承办人
	private String originEmployeeNO;		//交办人
	
	private Date deadLine;					//完成期限
	
	private String eventContent;			//交办事项内容
	private String eventDetail;				//实际处理过程及结果
	public String getProxyEmployeeNO() {
		return proxyEmployeeNO;
	}
	public void setProxyEmployeeNO(String proxyEmployeeNO) {
		this.proxyEmployeeNO = proxyEmployeeNO;
	}
	public String getOriginEmployeeNO() {
		return originEmployeeNO;
	}
	public void setOriginEmployeeNO(String originEmployeeNO) {
		this.originEmployeeNO = originEmployeeNO;
	}
	public Date getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
	
	@Column(columnDefinition="TEXT")
	public String getEventContent() {
		return eventContent;
	}
	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}
	
	@Column(columnDefinition="TEXT")
	public String getEventDetail() {
		return eventDetail;
	}
	public void setEventDetail(String eventDetail) {
		this.eventDetail = eventDetail;
	}
	
	
}
