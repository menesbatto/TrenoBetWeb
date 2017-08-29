package app.logic._2_matchResultAnalyzer.model;

import java.io.Serializable;

import app.utils.Utils;


public class GoalsStatsBean implements Serializable {
	
	private static final long serialVersionUID = 7120211051979245624L;

	private Integer strikedGoalsTotal;
	private Integer takenGoalsTotal;
	private Integer totalGoals;
	
	private Integer under0_5Hit;
	private Integer under1_5Hit;
	private Integer under2_5Hit;
	private Integer under3_5Hit;
	private Integer under4_5Hit;
	private Double under0_5Perc;
	private Double under1_5Perc;
	private Double under2_5Perc;
	private Double under3_5Perc;
	private Double under4_5Perc;
	
	private Integer over0_5Hit;
	private Integer over1_5Hit;
	private Integer over2_5Hit;
	private Integer over3_5Hit;
	private Integer over4_5Hit;
	private Double over0_5Perc;
	private Double over1_5Perc;
	private Double over2_5Perc;
	private Double over3_5Perc;
	private Double over4_5Perc;
	
	private Integer totalMatches;

	private String teamName;
	
	
	public GoalsStatsBean() {

		under0_5Hit = 0;
		under1_5Hit = 0;
		under2_5Hit = 0;
		under3_5Hit = 0;
		under4_5Hit = 0;
		
		over0_5Hit = 0;
		over1_5Hit = 0;
		over2_5Hit = 0;
		over3_5Hit = 0;
		over4_5Hit = 0;
		over0_5Hit = 0;
	
	}

	public Integer getStrikedGoalsTotal() {
		return strikedGoalsTotal;
	}

	public void setStrikedGoalsTotal(Integer strikedGoalsTotal) {
		this.strikedGoalsTotal = strikedGoalsTotal;
	}

	public Integer getTakenGoalsTotal() {
		return takenGoalsTotal;
	}

	public void setTakenGoalsTotal(Integer takenGoalsTotal) {
		this.takenGoalsTotal = takenGoalsTotal;
	}

	public Integer getUnder0_5Hit() {
		return under0_5Hit;
	}

	public void setUnder0_5Hit(Integer under0_5Hit) {
		this.under0_5Hit = under0_5Hit;
	}

	public Integer getUnder1_5Hit() {
		return under1_5Hit;
	}

	public void setUnder1_5Hit(Integer under1_5Hit) {
		this.under1_5Hit = under1_5Hit;
	}

	public Integer getUnder2_5Hit() {
		return under2_5Hit;
	}

	public void setUnder2_5Hit(Integer under2_5Hit) {
		this.under2_5Hit = under2_5Hit;
	}

	public Integer getUnder3_5Hit() {
		return under3_5Hit;
	}

	public void setUnder3_5Hit(Integer under3_5Hit) {
		this.under3_5Hit = under3_5Hit;
	}

	public Integer getUnder4_5Hit() {
		return under4_5Hit;
	}

	public void setUnder4_5Hit(Integer under4_5Hit) {
		this.under4_5Hit = under4_5Hit;
	}

	public Double getUnder0_5Perc() {
		return under0_5Perc;
	}

	public void setUnder0_5Perc(Double under0_5Perc) {
		this.under0_5Perc = under0_5Perc;
	}

	public Double getUnder1_5Perc() {
		return under1_5Perc;
	}

	public void setUnder1_5Perc(Double under1_5Perc) {
		this.under1_5Perc = under1_5Perc;
	}

	public Double getUnder2_5Perc() {
		return under2_5Perc;
	}

	public void setUnder2_5Perc(Double under2_5Perc) {
		this.under2_5Perc = under2_5Perc;
	}

	public Double getUnder3_5Perc() {
		return under3_5Perc;
	}

	public void setUnder3_5Perc(Double under3_5Perc) {
		this.under3_5Perc = under3_5Perc;
	}

	public Double getUnder4_5Perc() {
		return under4_5Perc;
	}

	public void setUnder4_5Perc(Double under4_5Perc) {
		this.under4_5Perc = under4_5Perc;
	}

	public Integer getOver0_5Hit() {
		return over0_5Hit;
	}

	public void setOver0_5Hit(Integer over0_5Hit) {
		this.over0_5Hit = over0_5Hit;
	}

	public Integer getOver1_5Hit() {
		return over1_5Hit;
	}

	public void setOver1_5Hit(Integer over1_5Hit) {
		this.over1_5Hit = over1_5Hit;
	}

	public Integer getOver2_5Hit() {
		return over2_5Hit;
	}

	public void setOver2_5Hit(Integer over2_5Hit) {
		this.over2_5Hit = over2_5Hit;
	}

	public Integer getOver3_5Hit() {
		return over3_5Hit;
	}

	public void setOver3_5Hit(Integer over3_5Hit) {
		this.over3_5Hit = over3_5Hit;
	}

	public Integer getOver4_5Hit() {
		return over4_5Hit;
	}

	public void setOver4_5Hit(Integer over4_5Hit) {
		this.over4_5Hit = over4_5Hit;
	}

	public Double getOver0_5Perc() {
		return over0_5Perc;
	}

	public void setOver0_5Perc(Double over0_5Perc) {
		this.over0_5Perc = over0_5Perc;
	}

	public Double getOver1_5Perc() {
		return over1_5Perc;
	}

	public void setOver1_5Perc(Double over1_5Perc) {
		this.over1_5Perc = over1_5Perc;
	}

	public Double getOver2_5Perc() {
		return over2_5Perc;
	}

	public void setOver2_5Perc(Double over2_5Perc) {
		this.over2_5Perc = over2_5Perc;
	}

	public Double getOver3_5Perc() {
		return over3_5Perc;
	}

	public void setOver3_5Perc(Double over3_5Perc) {
		this.over3_5Perc = over3_5Perc;
	}

	public Double getOver4_5Perc() {
		return over4_5Perc;
	}

	public void setOver4_5Perc(Double over4_5Perc) {
		this.over4_5Perc = over4_5Perc;
	}

	public Integer getTotalMatches() {
		return totalMatches;
	}

	public void setTotalMatches(Integer totalMatches) {
		this.totalMatches = totalMatches;
	}

	public Integer getTotalGoals() {
		return totalGoals;
	}

	public void setTotalGoals(Integer totalGoalsTotal) {
		this.totalGoals = totalGoalsTotal;
	}

	@Override
	public String toString() {
		return "\tGF " + strikedGoalsTotal + "\tGS " + takenGoalsTotal+ "\tG " + totalGoals + "\tmatch " + totalMatches + "\n" +
				"\nUNDER" + "\t0,5\t" + "1,5\t" + "2,5\t" + "3,5\t" + "4,5\n" +
				
				"#\t" + under0_5Hit + "\t"	+ under1_5Hit + "\t" + under2_5Hit + "\t" + under3_5Hit + "\t"	+ under4_5Hit + ", " + "\n" +
				"%\t" + Utils.redimString(under0_5Perc) + "\t" + Utils.redimString(under1_5Perc) + "\t"	+ Utils.redimString(under2_5Perc) + "\t" + Utils.redimString(under3_5Perc) + "\t" + Utils.redimString(under4_5Perc) + "\n" + 
				
				"\nOVER" + "\t0,5\t" + "1,5\t" + "2,5\t" + "3,5\t" + "4,5\n" +

				"#\t" + over0_5Hit + "\t"	+ over1_5Hit + "\t" + over2_5Hit + "\t" + over3_5Hit + "\t"	+ over4_5Hit + ", " + "\n" +
				"%\t" + Utils.redimString(over0_5Perc) + "\t" + Utils.redimString(over1_5Perc) + "\t"	+ Utils.redimString(over2_5Perc) + "\t" + Utils.redimString(over3_5Perc) + "\t" + Utils.redimString(over4_5Perc) + "\n" +
				
				"\n\n";
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	
	
	
}
