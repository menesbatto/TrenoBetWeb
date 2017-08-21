package app.logic.app._0_eventsOddsDownloader.model;

import java.io.Serializable;

public class ChampBean implements Serializable{

	private static final long serialVersionUID = -2059941999587436150L;
	private int id;
	private String name;
	private int startYear;
	private int endYear;
	private String nation;


	public ChampBean() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	
	
}