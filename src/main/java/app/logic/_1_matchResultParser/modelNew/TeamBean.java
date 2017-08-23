package app.logic._1_matchResultParser.modelNew;

import java.io.Serializable;

public class TeamBean implements Serializable{

	private static final long serialVersionUID = 553678484447314578L;

	private int id;

	private String name;
	
	private ChampBean champ;

	public TeamBean() {
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

	public ChampBean getChamp() {
		return champ;
	}

	public void setChamp(ChampBean champ) {
		this.champ = champ;
	}

	
	
	
}
