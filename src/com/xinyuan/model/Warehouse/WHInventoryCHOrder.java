package com.xinyuan.model.Warehouse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;

/**
 * 仓库库存异动单
 *
 */

@Entity
@Table
public class WHInventoryCHOrder extends OrderApp4 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// _O for Old , _N for New.
	
	private String productCode_O;         //产品代码
	private String productName_O;         //品   名
	private float totalAmount_O;          //总数量
	private float lendAmount_O;           //借出数量
	private String basicUnit_O;           //基本单位
	private float amount_O;               //数量
	private String unit_O;                //单位
	private float priceBasicUnit_O;       //平均价格_基本单位
	private String productLocation_O;     //产品位置
	private String productDesc_O;         //产品描述
	private String productDescPDF_O;      //产品描述PDF
	
	
	
	private String productCode_N;         
	private String productName_N;         
	private float totalAmount_N = -1;          
	private float lendAmount_N = -1;           
	private String basicUnit_N;           
	private float amount_N = -1;               
	private String unit_N;                
	private float priceBasicUnit_N = -1;       
	private String productLocation_N;     
	private String productDesc_N;
	private String productDescPDF_N;  
	
	
	
	public String getProductCode_O() {
		return productCode_O;
	}
	public void setProductCode_O(String productCode_O) {
		this.productCode_O = productCode_O;
	}
	public String getProductName_O() {
		return productName_O;
	}
	public void setProductName_O(String productName_O) {
		this.productName_O = productName_O;
	}
	
	public float getTotalAmount_O() {
		return totalAmount_O;
	}
	public void setTotalAmount_O(float totalAmount_O) {
		this.totalAmount_O = totalAmount_O;
	}

	public float getLendAmount_O() {
		return lendAmount_O;
	}
	public void setLendAmount_O(float lendAmount_O) {
		this.lendAmount_O = lendAmount_O;
	}
	public String getBasicUnit_O() {
		return basicUnit_O;
	}
	public void setBasicUnit_O(String basicUnit_O) {
		this.basicUnit_O = basicUnit_O;
	}
	
	public float getAmount_O() {
		return amount_O;
	}
	public void setAmount_O(float amount_O) {
		this.amount_O = amount_O;
	}
	public String getUnit_O() {
		return unit_O;
	}
	public void setUnit_O(String unit_O) {
		this.unit_O = unit_O;
	}
	
	public float getPriceBasicUnit_O() {
		return priceBasicUnit_O;
	}
	public void setPriceBasicUnit_O(float priceBasicUnit_O) {
		this.priceBasicUnit_O = priceBasicUnit_O;
	}
	
	@Column(columnDefinition = "TEXT")
	public String getProductLocation_O() {
		return productLocation_O;
	}
	public void setProductLocation_O(String productLocation_O) {
		this.productLocation_O = productLocation_O;
	}
	
	@Column(columnDefinition = "TEXT")
	public String getProductDesc_O() {
		return productDesc_O;
	}
	public void setProductDesc_O(String productDesc_O) {
		this.productDesc_O = productDesc_O;
	}
	public String getProductCode_N() {
		return productCode_N;
	}
	public void setProductCode_N(String productCode_N) {
		this.productCode_N = productCode_N;
	}
	public String getProductName_N() {
		return productName_N;
	}
	public void setProductName_N(String productName_N) {
		this.productName_N = productName_N;
	}
	
	public float getTotalAmount_N() {
		return totalAmount_N;
	}
	public void setTotalAmount_N(float totalAmount_N) {
		this.totalAmount_N = totalAmount_N;
	}
	
	public float getLendAmount_N() {
		return lendAmount_N;
	}
	public void setLendAmount_N(float lendAmount_N) {
		this.lendAmount_N = lendAmount_N;
	}
	public String getBasicUnit_N() {
		return basicUnit_N;
	}
	public void setBasicUnit_N(String basicUnit_N) {
		this.basicUnit_N = basicUnit_N;
	}
	public float getAmount_N() {
		return amount_N;
	}
	public void setAmount_N(float amount_N) {
		this.amount_N = amount_N;
	}
	public String getUnit_N() {
		return unit_N;
	}
	public void setUnit_N(String unit_N) {
		this.unit_N = unit_N;
	}
	
	public float getPriceBasicUnit_N() {
		return priceBasicUnit_N;
	}
	public void setPriceBasicUnit_N(float priceBasicUnit_N) {
		this.priceBasicUnit_N = priceBasicUnit_N;
	}
	
	@Column(columnDefinition = "TEXT")
	public String getProductLocation_N() {
		return productLocation_N;
	}
	public void setProductLocation_N(String productLocation_N) {
		this.productLocation_N = productLocation_N;
	}
	
	@Column(columnDefinition = "TEXT")
	public String getProductDesc_N() {
		return productDesc_N;
	}
	public void setProductDesc_N(String productDesc_N) {
		this.productDesc_N = productDesc_N;
	}
	
	@Column(columnDefinition = "TEXT")
	public String getProductDescPDF_O() {
		return productDescPDF_O;
	}
	public void setProductDescPDF_O(String productDescPDF_O) {
		this.productDescPDF_O = productDescPDF_O;
	}
	
	@Column(columnDefinition = "TEXT")
	public String getProductDescPDF_N() {
		return productDescPDF_N;
	}
	public void setProductDescPDF_N(String productDescPDF_N) {
		this.productDescPDF_N = productDescPDF_N;
	}         
	
	
	
	

}
