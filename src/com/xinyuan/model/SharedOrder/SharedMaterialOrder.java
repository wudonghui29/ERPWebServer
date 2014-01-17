package com.xinyuan.model.SharedOrder;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp3;

/**
 * 
 * @author bravo
 *
 */

@Entity
@Table
public class SharedMaterialOrder extends OrderApp3 {
	private static final long serialVersionUID = 1L;

	private String contractNO;		//合同单号
	
	private String format;			//形式
	
	private String projectName;		//工程名称
	
	private float planesize;		//平面尺寸
	
	private float drippingHeight; 	//滴水高度
	
	private float brickWallHeight;  //砖墙高度

	private float pillarBottomHeight;//柱底高度
	
	private float pillarDistance;	//柱距
	
	private String pillarSpec;		//钢柱规格
	
	private String doorSpec;		//门规格
	
	private String girderSpec;		//钢梁规格
	
	private String roofPurlinSpec;	//屋面檀条规格
	
	private String wallPurlinSpec;	//墙面檀条规格
	
	private String purlinPaintSpec;	//檀条面漆规格
	
	private String downPipes;		//落水管
	
	private Date baitingTime;		//下料时间
	
	private Date productionTime;	//制作时间
	
	private Date basicSetupTime;	//基础安装时间
	
	private Date steelStructureSetupTime; //钢架安装时间
	
	private int projectDuration;	//工期
	
	private float drainSlop;		//排水坡度
	
	private String sink;			//水槽
	
	private String dormerType;		//气楼型式
	
	private String vertialGirder;	//纵行梁
	
	private String insulationLayer;	//隔热层
	
	private String lightingBoard;	//采光板
	
	private String weighPay;		//过磅付款
	
	private String partyCheckWeigh;	//甲方看磅
	
	private String constructReport;	//报建
	
	private String inspection;		//送检
	
	private String hemmingColor;	//包边颜色
	
	private String colorBoardRoofColor;	//彩板颜色：屋面
	private String colorBoardWallColor; //彩板颜色：墙面
	
	private String roofColorBoardSpec;	//屋面彩板：规格
	private int roofColorBoardQuantity;	//屋面彩板: 数量
	
	private String steelBottomPaintRoofTruss;	//钢架底漆： 屋架
	private String steelBottomPaintDormer;		//钢架底漆： 气楼
	
	private String steelSurfacePaintRoofTruss;	//钢架面漆： 屋架
	private String steelSurfacePaintDormer;		//钢架面漆： 气楼
	
	
	public String getContractNO() {
		return contractNO;
	}
	public void setContractNO(String contractNO) {
		this.contractNO = contractNO;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public float getPlanesize() {
		return planesize;
	}
	public void setPlanesize(float planesize) {
		this.planesize = planesize;
	}
	public float getDrippingHeight() {
		return drippingHeight;
	}
	public void setDrippingHeight(float drippingHeight) {
		this.drippingHeight = drippingHeight;
	}
	public float getBrickWallHeight() {
		return brickWallHeight;
	}
	public void setBrickWallHeight(float brickWallHeight) {
		this.brickWallHeight = brickWallHeight;
	}
	public float getPillarBottomHeight() {
		return pillarBottomHeight;
	}
	public void setPillarBottomHeight(float pillarBottomHeight) {
		this.pillarBottomHeight = pillarBottomHeight;
	}
	public float getPillarDistance() {
		return pillarDistance;
	}
	public void setPillarDistance(float pillarDistance) {
		this.pillarDistance = pillarDistance;
	}
	public String getPillarSpec() {
		return pillarSpec;
	}
	public void setPillarSpec(String pillarSpec) {
		this.pillarSpec = pillarSpec;
	}
	public String getDoorSpec() {
		return doorSpec;
	}
	public void setDoorSpec(String doorSpec) {
		this.doorSpec = doorSpec;
	}
	public String getGirderSpec() {
		return girderSpec;
	}
	public void setGirderSpec(String girderSpec) {
		this.girderSpec = girderSpec;
	}
	public String getRoofPurlinSpec() {
		return roofPurlinSpec;
	}
	public void setRoofPurlinSpec(String roofPurlinSpec) {
		this.roofPurlinSpec = roofPurlinSpec;
	}
	public String getWallPurlinSpec() {
		return wallPurlinSpec;
	}
	public void setWallPurlinSpec(String wallPurlinSpec) {
		this.wallPurlinSpec = wallPurlinSpec;
	}
	public String getPurlinPaintSpec() {
		return purlinPaintSpec;
	}
	public void setPurlinPaintSpec(String purlinPaintSpec) {
		this.purlinPaintSpec = purlinPaintSpec;
	}
	public String getDownPipes() {
		return downPipes;
	}
	public void setDownPipes(String downPipes) {
		this.downPipes = downPipes;
	}
	public Date getBaitingTime() {
		return baitingTime;
	}
	public void setBaitingTime(Date baitingTime) {
		this.baitingTime = baitingTime;
	}
	public Date getProductionTime() {
		return productionTime;
	}
	public void setProductionTime(Date productionTime) {
		this.productionTime = productionTime;
	}
	public Date getBasicSetupTime() {
		return basicSetupTime;
	}
	public void setBasicSetupTime(Date basicSetupTime) {
		this.basicSetupTime = basicSetupTime;
	}
	public Date getSteelStructureSetupTime() {
		return steelStructureSetupTime;
	}
	public void setSteelStructureSetupTime(Date steelStructureSetupTime) {
		this.steelStructureSetupTime = steelStructureSetupTime;
	}
	public int getProjectDuration() {
		return projectDuration;
	}
	public void setProjectDuration(int projectDuration) {
		this.projectDuration = projectDuration;
	}
	public float getDrainSlop() {
		return drainSlop;
	}
	public void setDrainSlop(float drainSlop) {
		this.drainSlop = drainSlop;
	}
	public String getSink() {
		return sink;
	}
	public void setSink(String sink) {
		this.sink = sink;
	}
	public String getDormerType() {
		return dormerType;
	}
	public void setDormerType(String dormerType) {
		this.dormerType = dormerType;
	}
	public String getVertialGirder() {
		return vertialGirder;
	}
	public void setVertialGirder(String vertialGirder) {
		this.vertialGirder = vertialGirder;
	}
	public String getInsulationLayer() {
		return insulationLayer;
	}
	public void setInsulationLayer(String insulationLayer) {
		this.insulationLayer = insulationLayer;
	}
	public String getLightingBoard() {
		return lightingBoard;
	}
	public void setLightingBoard(String lightingBoard) {
		this.lightingBoard = lightingBoard;
	}
	public String getWeighPay() {
		return weighPay;
	}
	public void setWeighPay(String weighPay) {
		this.weighPay = weighPay;
	}
	public String getPartyCheckWeigh() {
		return partyCheckWeigh;
	}
	public void setPartyCheckWeigh(String partyCheckWeigh) {
		this.partyCheckWeigh = partyCheckWeigh;
	}
	public String getConstructReport() {
		return constructReport;
	}
	public void setConstructReport(String constructReport) {
		this.constructReport = constructReport;
	}
	public String getInspection() {
		return inspection;
	}
	public void setInspection(String inspection) {
		this.inspection = inspection;
	}
	public String getHemmingColor() {
		return hemmingColor;
	}
	public void setHemmingColor(String hemmingColor) {
		this.hemmingColor = hemmingColor;
	}
	public String getColorBoardRoofColor() {
		return colorBoardRoofColor;
	}
	public void setColorBoardRoofColor(String colorBoardRoofColor) {
		this.colorBoardRoofColor = colorBoardRoofColor;
	}
	public String getColorBoardWallColor() {
		return colorBoardWallColor;
	}
	public void setColorBoardWallColor(String colorBoardWallColor) {
		this.colorBoardWallColor = colorBoardWallColor;
	}
	public String getRoofColorBoardSpec() {
		return roofColorBoardSpec;
	}
	public void setRoofColorBoardSpec(String roofColorBoardSpec) {
		this.roofColorBoardSpec = roofColorBoardSpec;
	}
	public int getRoofColorBoardQuantity() {
		return roofColorBoardQuantity;
	}
	public void setRoofColorBoardQuantity(int roofColorBoardQuantity) {
		this.roofColorBoardQuantity = roofColorBoardQuantity;
	}
	public String getSteelBottomPaintRoofTruss() {
		return steelBottomPaintRoofTruss;
	}
	public void setSteelBottomPaintRoofTruss(String steelBottomPaintRoofTruss) {
		this.steelBottomPaintRoofTruss = steelBottomPaintRoofTruss;
	}
	public String getSteelBottomPaintDormer() {
		return steelBottomPaintDormer;
	}
	public void setSteelBottomPaintDormer(String steelBottomPaintDormer) {
		this.steelBottomPaintDormer = steelBottomPaintDormer;
	}
	public String getSteelSurfacePaintRoofTruss() {
		return steelSurfacePaintRoofTruss;
	}
	public void setSteelSurfacePaintRoofTruss(String steelSurfacePaintRoofTruss) {
		this.steelSurfacePaintRoofTruss = steelSurfacePaintRoofTruss;
	}
	public String getSteelSurfacePaintDormer() {
		return steelSurfacePaintDormer;
	}
	public void setSteelSurfacePaintDormer(String steelSurfacePaintDormer) {
		this.steelSurfacePaintDormer = steelSurfacePaintDormer;
	}
	
}
