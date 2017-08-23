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

import app.dao.tipologiche.entities.RankingCriteria;
import app.utils.RankCritEnum;

@Entity
public class ChampRanCri {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ElementCollection
	private List<RankingCriteria> criteria;
	
	@OneToOne//(cascade = {CascadeType.ALL})
	private Champ champ;
	
	public ChampRanCri() {
	}
	
	public ChampRanCri(List<RankingCriteria> criteria) {
		super();
		this.criteria = criteria;
	}

	public ChampRanCri(Champ champ, List<RankingCriteria> criteria) {
		super();
		this.champ = champ;
		this.criteria = criteria;
	}


	public List<RankingCriteria> getPositions() {
		return criteria;
	}

	public void setPositions(List<RankingCriteria> criteria) {
		this.criteria = criteria;
	}

	public Champ getChamp() {
		return champ;
	}

	public void setChamp(Champ champ) {
		this.champ = champ;
	}




}
