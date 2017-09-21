package app.logic._1_matchesDownlaoder.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.hateoas.TemplateVariable.VariableType;

import app.dao.tipologiche.entities.HomeVariationType;

public class ResultGoodnessBean implements Serializable{
	
	private static final long serialVersionUID = -8220296671620254113L;
//
//	private Double goodnessWfinal;
//	private Double goodnessDfinal;
//	private Double goodnessLfinal;
//
//	private Double goodnessW;
//	private Double goodnessD;
//	private Double goodnessL;
//	
//	private Double goodnessWwithTrends;
//	private Double goodnessDwithTrends;
//	private Double goodnessLwithTrends;
//
//	private Double goodnessWwithMotivation;
//	private Double goodnessDwithMotivation;
//	private Double goodnessLwithMotivation;
//	
	private ResultGoodnessWDLBean winFinal;
	
	private ResultGoodnessWDLBean winClean;
	
	private ResultGoodnessWDLBean winTrend;
	
	private ResultGoodnessWDLBean winMotivation;
	
	private Map<UoThresholdEnum, ResultGoodnessUoBean> uoGoodness;

	private Map<HomeVariationEnum, ResultGoodnessWDLBean> ehGoodness;
	
	
	public ResultGoodnessBean() {
		uoGoodness = new HashMap<UoThresholdEnum, ResultGoodnessUoBean>();
		ehGoodness = new HashMap<HomeVariationEnum, ResultGoodnessWDLBean>();
	}
//	public Double getGoodnessW() {
//		return goodnessW;
//	}
//	public void setGoodnessW(Double goodnessW) {
//		this.goodnessW = goodnessW;
//	}
//	public Double getGoodnessD() {
//		return goodnessD;
//	}
//	public void setGoodnessD(Double goodnessD) {
//		this.goodnessD = goodnessD;
//	}
//	public Double getGoodnessL() {
//		return goodnessL;
//	}
//	public void setGoodnessL(Double goodnessL) {
//		this.goodnessL = goodnessL;
//	}
//	@Override
//	public String toString() {
//		return  "\t" + goodnessW + "\t" + goodnessD + "\t" + goodnessL + "\n" +
//				"\t" + goodnessU + "\t" + goodnessO;
//	}
//	@Override
//	public String toString() {
//		return  "\t" + goodnessW + "\t" + goodnessD + "\t" + goodnessL + "\n";
//				
//	}
//	
//	public Double getGoodnessDwithMotivation() {
//		return goodnessDwithMotivation;
//	}
//	public void setGoodnessDwithMotivation(Double goodnessDwithMotivation) {
//		this.goodnessDwithMotivation = goodnessDwithMotivation;
//	}
//	
//	public Double getGoodnessDwithTrends() {
//		return goodnessDwithTrends;
//	}
//	public void setGoodnessDwithTrends(Double goodnessDwithTrends) {
//		this.goodnessDwithTrends = goodnessDwithTrends;
//	}
//	
//	public Double getGoodnessWwithTrends() {
//		return goodnessWwithTrends;
//	}
//	public void setGoodnessWwithTrends(Double goodnessWwithTrends) {
//		this.goodnessWwithTrends = goodnessWwithTrends;
//	}
//	public Double getGoodnessLwithTrends() {
//		return goodnessLwithTrends;
//	}
//	public void setGoodnessLwithTrends(Double goodnessLwithTrends) {
//		this.goodnessLwithTrends = goodnessLwithTrends;
//	}
//	public Double getGoodnessWwithMotivation() {
//		return goodnessWwithMotivation;
//	}
//	public void setGoodnessWwithMotivation(Double goodnessWwithMotivation) {
//		this.goodnessWwithMotivation = goodnessWwithMotivation;
//	}
//	public Double getGoodnessLwithMotivation() {
//		return goodnessLwithMotivation;
//	}
//	public void setGoodnessLwithMotivation(Double goodnessLwithMotivation) {
//		this.goodnessLwithMotivation = goodnessLwithMotivation;
//	}
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//	public Double getGoodnessWfinal() {
//		return goodnessWfinal;
//	}
//	public void setGoodnessWfinal(Double goodnessWfinal) {
//		this.goodnessWfinal = goodnessWfinal;
//	}
//	public Double getGoodnessDfinal() {
//		return goodnessDfinal;
//	}
//	public void setGoodnessDfinal(Double goodnessDfinal) {
//		this.goodnessDfinal = goodnessDfinal;
//	}
//	public Double getGoodnessLfinal() {
//		return goodnessLfinal;
//	}
//	public void setGoodnessLfinal(Double goodnessLfinal) {
//		this.goodnessLfinal = goodnessLfinal;
//	}
	public Map<UoThresholdEnum, ResultGoodnessUoBean> getUoGoodness() {
		return uoGoodness;
	}
	public void setUoGoodness(Map<UoThresholdEnum, ResultGoodnessUoBean> uoGoodness) {
		this.uoGoodness = uoGoodness;
	}
	public Map<HomeVariationEnum, ResultGoodnessWDLBean> getEhGoodness() {
		return ehGoodness;
	}
	public void setEhGoodness(Map<HomeVariationEnum, ResultGoodnessWDLBean> ehGoodness) {
		this.ehGoodness = ehGoodness;
	}
	public ResultGoodnessWDLBean getWinFinal() {
		return winFinal;
	}
	public void setWinFinal(ResultGoodnessWDLBean winFinal) {
		this.winFinal = winFinal;
	}
	public ResultGoodnessWDLBean getWinClean() {
		return winClean;
	}
	public void setWinClean(ResultGoodnessWDLBean winClean) {
		this.winClean = winClean;
	}
	public ResultGoodnessWDLBean getWinTrend() {
		return winTrend;
	}
	public void setWinTrend(ResultGoodnessWDLBean winTrend) {
		this.winTrend = winTrend;
	}
	public ResultGoodnessWDLBean getWinMotivation() {
		return winMotivation;
	}
	public void setWinMotivation(ResultGoodnessWDLBean winMotivation) {
		this.winMotivation = winMotivation;
	}
	
}
