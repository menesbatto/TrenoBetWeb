package app.dao.tabelle.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import app.utils.RankCritEnum;

@Entity
public class ChampImpPos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ElementCollection
	private List<Integer> positions;
	
	@OneToOne//(cascade = {CascadeType.ALL})
	private Champ champ;
	
	public ChampImpPos() {
	}

	
	public ChampImpPos(List<Integer> positions) {
		super();
		this.positions = positions;
	}

	public ChampImpPos(Champ champ, List<Integer> positions) {
		super();
		this.champ = champ;
		this.positions = positions;
	}


	public List<Integer> getPositions() {
		return positions;
	}

	public void setPositions(List<Integer> positions) {
		this.positions = positions;
	}

	public Champ getChamp() {
		return champ;
	}

	public void setChamp(Champ champ) {
		this.champ = champ;
	}




}
