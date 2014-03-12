package com.xinyuan.model.Finance;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.BaseBill;

@Table
@Entity
public class FinancePaymentBill extends BaseBill {

	private static final long serialVersionUID = 1L;
	
	private String paymentOrderNO;			// 支付单号
	
	private String referenceOrderNO;		// 单号
	private String productName;				// 品名
	
	private float realPaid;					// 实付金额

	
	public String getPaymentOrderNO() {
		return paymentOrderNO;
	}

	public void setPaymentOrderNO(String paymentOrderNO) {
		this.paymentOrderNO = paymentOrderNO;
	}

	public String getReferenceOrderNO() {
		return referenceOrderNO;
	}

	public void setReferenceOrderNO(String referenceOrderNO) {
		this.referenceOrderNO = referenceOrderNO;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getRealPaid() {
		return realPaid;
	}

	public void setRealPaid(float realPaid) {
		this.realPaid = realPaid;
	}
}
