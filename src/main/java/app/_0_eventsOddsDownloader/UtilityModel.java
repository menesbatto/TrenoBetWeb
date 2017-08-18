package app._0_eventsOddsDownloader;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app._0_eventsOddsDownloader.model._1X2OddsBean;
import app.dao._1X2OddsDao;
import app.dao.BetHouseDao;
import app.dao.ChampDao;
import app.dao.MatchoDao;
import app.dao.TeamDao;
import app.dao.TimeTypeDao;

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
	
	
	
	
	
	public void execute() {
		betHouseDao.initTable();
		timeTypeDao.initTable();
		champDao.initTable();
		teamDao.initTable();
		
		
	}
	
}
