package app.dao.tabelle.entities;

import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import app.dao.tipologiche.entities.UoThresholdType;

@Entity
public class ResultGoodness {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne
	private EventOdds eventOdds;
	
	private Double goodnessWfinal;
	private Double goodnessDfinal;
	private Double goodnessLfinal;

	private Double goodnessW;
	private Double goodnessD;
	private Double goodnessL;
	
	private Double goodnessWwithTrends;
	private Double goodnessDwithTrends;
	private Double goodnessLwithTrends;

	private Double goodnessWwithMotivation;
	private Double goodnessDwithMotivation;
	private Double goodnessLwithMotivation;
	
	@OneToMany
	private List<ResultGoodnessUo> uo;

	@OneToMany
	private List<ResultGoodnessWDL> eh;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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



	public Double getGoodnessDwithTrends() {
		return goodnessDwithTrends;
	}


	public void setGoodnessDwithTrends(Double goodnessDwithTrends) {
		this.goodnessDwithTrends = goodnessDwithTrends;
	}




	public Double getGoodnessDwithMotivation() {
		return goodnessDwithMotivation;
	}


	public void setGoodnessDwithMotivation(Double goodnessDwithMotivation) {
		this.goodnessDwithMotivation = goodnessDwithMotivation;
	}


	public EventOdds getEventOdds() {
		return eventOdds;
	}


	public void setEventOdds(EventOdds eventOdds) {
		this.eventOdds = eventOdds;
	}


	public Double getGoodnessWwithTrends() {
		return goodnessWwithTrends;
	}


	public void setGoodnessWwithTrends(Double goodnessWwithTrends) {
		this.goodnessWwithTrends = goodnessWwithTrends;
	}


	public Double getGoodnessLwithTrends() {
		return goodnessLwithTrends;
	}


	public void setGoodnessLwithTrends(Double goodnessLwithTrends) {
		this.goodnessLwithTrends = goodnessLwithTrends;
	}


	public Double getGoodnessWwithMotivation() {
		return goodnessWwithMotivation;
	}


	public void setGoodnessWwithMotivation(Double goodnessWwithMotivation) {
		this.goodnessWwithMotivation = goodnessWwithMotivation;
	}


	public Double getGoodnessLwithMotivation() {
		return goodnessLwithMotivation;
	}


	public void setGoodnessLwithMotivation(Double goodnessLwithMotivation) {
		this.goodnessLwithMotivation = goodnessLwithMotivation;
	}


	public List<ResultGoodnessUo> getUo() {
		return uo;
	}


	public void setUo(List<ResultGoodnessUo> uo) {
		this.uo = uo;
	}


	public List<ResultGoodnessWDL> getEh() {
		return eh;
	}


	public void setEh(List<ResultGoodnessWDL> eh) {
		this.eh = eh;
	}


	
}
