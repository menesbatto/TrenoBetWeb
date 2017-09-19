package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResultGoodness implements Serializable{
	
	private static final long serialVersionUID = -8220296671620254113L;

	private Double goodnessWfinal;
	private Double goodnessDfinal;
	private Double goodnessLfinal;

	private Double goodnessW;
	private Double goodnessD;
	private Double goodnessL;
	
	private Double goodnessHwithTrends;
	private Double goodnessDwithTrends;
	private Double goodnessAwithTrends;

	private Double goodnessHwithMotivation;
	private Double goodnessDwithMotivation;
	private Double goodnessAwithMotivation;
	
	private Double goodnessU;
	private Double goodnessO;
	
	private Map<UoThresholdEnum, ResultGoodnessUo> uoMap;
	
	
	public ResultGoodness() {
		uoMap = new HashMap<UoThresholdEnum, ResultGoodnessUo>();
	}
	public Double getGoodnessW() {
		return goodnessW;
	}
	public void setGoodnessW(Double goodnessW) {
		this.goodnessW = goodnessW;
	}
	public Double getGoodnessD() {
		return goodnessD;
	}
	public void setGoodnessD(Double goodnessD) {
		this.goodnessD = goodnessD;
	}
	public Double getGoodnessL() {
		return goodnessL;
	}
	public void setGoodnessL(Double goodnessL) {
		this.goodnessL = goodnessL;
	}
//	@Override
//	public String toString() {
//		return  "\t" + goodnessW + "\t" + goodnessD + "\t" + goodnessL + "\n" +
//				"\t" + goodnessU + "\t" + goodnessO;
//	}
	@Override
	public String toString() {
		return  "\t" + goodnessW + "\t" + goodnessD + "\t" + goodnessL + "\n" +
				"\t" + goodnessU + "\t" + goodnessO;
	}
	public Double getGoodnessU() {
		return goodnessU;
	}
	public void setGoodnessU(Double goodnessU) {
		this.goodnessU = goodnessU;
	}
	public Double getGoodnessO() {
		return goodnessO;
	}
	public void setGoodnessO(Double goodnessO) {
		this.goodnessO = goodnessO;
	}
	public Double getGoodnessWwithMotivation() {
		return goodnessHwithMotivation;
	}
	public void setGoodnessWwithMotivation(Double goodnessWwithMotivation) {
		this.goodnessHwithMotivation = goodnessWwithMotivation;
	}
	public Double getGoodnessDwithMotivation() {
		return goodnessDwithMotivation;
	}
	public void setGoodnessDwithMotivation(Double goodnessDwithMotivation) {
		this.goodnessDwithMotivation = goodnessDwithMotivation;
	}
	public Double getGoodnessLwithMotivation() {
		return goodnessAwithMotivation;
	}
	public void setGoodnessLwithMotivation(Double goodnessLwithMotivation) {
		this.goodnessAwithMotivation = goodnessLwithMotivation;
	}
	public Double getGoodnessWwithTrends() {
		return goodnessHwithTrends;
	}
	public void setGoodnessWwithTrends(Double goodnessWwithTrends) {
		this.goodnessHwithTrends = goodnessWwithTrends;
	}
	public Double getGoodnessDwithTrends() {
		return goodnessDwithTrends;
	}
	public void setGoodnessDwithTrends(Double goodnessDwithTrends) {
		this.goodnessDwithTrends = goodnessDwithTrends;
	}
	public Double getGoodnessLwithTrends() {
		return goodnessAwithTrends;
	}
	public void setGoodnessLwithTrends(Double goodnessLwithTrends) {
		this.goodnessAwithTrends = goodnessLwithTrends;
	}
	public Double getGoodnessWfinal() {
		return goodnessWfinal;
	}
	public void setGoodnessWfinal(Double goodnessWfinal) {
		this.goodnessWfinal = goodnessWfinal;
	}
	public Double getGoodnessDfinal() {
		return goodnessDfinal;
	}
	public void setGoodnessDfinal(Double goodnessDfinal) {
		this.goodnessDfinal = goodnessDfinal;
	}
	public Double getGoodnessLfinal() {
		return goodnessLfinal;
	}
	public void setGoodnessLfinal(Double goodnessLfinal) {
		this.goodnessLfinal = goodnessLfinal;
	}
	public Map<UoThresholdEnum, ResultGoodnessUo> getUoMap() {
		return uoMap;
	}
	public void setUoMap(Map<UoThresholdEnum, ResultGoodnessUo> uoMap) {
		this.uoMap = uoMap;
	}
	
	
	
}
