package com.xinyuan.model.QualityControl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;

@Entity
@Table
public class QCReworkNoticeOrder extends OrderApp4 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String projectName;   //工程名称
	
	private String producer;      //制作人员
	
	private String qcOrderNo;     //质检单号
	
	private String qualityDesc;   //质量问题描述
	
	private String problemReview; //问题检讨
	
	private float fee;            //费用  

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getQcOrderNo() {
		return qcOrderNo;
	}

	public void setQcOrderNo(String qcOrderNo) {
		this.qcOrderNo = qcOrderNo;
	}

	@Column(columnDefinition = "TEXT")
	public String getQualityDesc() {
		return qualityDesc;
	}

	public void setQualityDesc(String qualityDesc) {
		this.qualityDesc = qualityDesc;
	}

	@Column(columnDefinition = "TEXT")
	public String getProblemReview() {
		return problemReview;
	}

	public void setProblemReview(String problemReview) {
		this.problemReview = problemReview;
	}

	public float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}


	
}
