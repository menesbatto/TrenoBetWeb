package app.logic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.ChampDao;
import app.dao.tabelle.MatchoDao;
import app.dao.tabelle.TeamDao;
import app.dao.tabelle._1X2OddsDao;
import app.dao.tipologiche.BetHouseDao;
import app.dao.tipologiche.HomeVariationTypeDao;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.UoThresholdTypeDao;
import app.logic._1_matchResultParser.modelNew._1X2OddsBean;

@Service
public class UtilityModel {



	@Autowired
	private ChampDao champDao;

	@Autowired
	private TimeTypeDao timeTypeDao;

	@Autowired
	private BetHouseDao betHouseDao;

	@Autowired
	private TeamDao teamDao;

	@Autowired
	private UoThresholdTypeDao uoThresholdTypeDao;
	
	@Autowired
	private HomeVariationTypeDao homeVariationTypeao;
	
	
	
	
	
	public void execute() {
		betHouseDao.initTable();
		timeTypeDao.initTable();
//		champDao.initTable();
//		teamDao.initTable();
		uoThresholdTypeDao.initTable();
		homeVariationTypeao.initTable();
		
		
	}
	
}
