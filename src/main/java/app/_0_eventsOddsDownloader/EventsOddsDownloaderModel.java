package app._0_eventsOddsDownloader;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app._0_eventsOddsDownloader.model.ChampBean;
import app._0_eventsOddsDownloader.model.MatchBean;
import app._0_eventsOddsDownloader.model.TeamBean;
import app._0_eventsOddsDownloader.model._1X2OddsBean;
import app.dao._1X2OddsDao;
import app.dao.BetHouseDao;
import app.dao.ChampDao;
import app.dao.TeamDao;

@Service
public class EventsOddsDownloaderModel {



	@Autowired
	private _1X2OddsDao betDao;


	@Autowired
	private BetHouseDao betHouseDao;
	@Autowired
	private TeamDao teamDao;
	@Autowired
	private ChampDao champDao;
//	@Autowired
//	private MatchDao matchDao;
	
	
	
	
	public _1X2OddsBean execute() {
		MatchBean matchBean = new MatchBean();
		ChampBean champ = new ChampBean();
		champ.setName("SerieA");
		champ.setStartYear(2017);
		champ.setNation("Italia");
		matchBean.setChamp(champ);
		
		TeamBean homeTeam = new TeamBean();
		homeTeam.setName("juventus");
		matchBean.setHomeTeam(homeTeam);

		TeamBean awayTeam = new TeamBean();
		awayTeam.setName("pescara");
		matchBean.setAwayTeam(awayTeam);
		
		matchBean.setFullTimeHomeGoals(2);
		matchBean.setFullTimeAwayGoals(3);
		
		matchBean.setHalfTimeHomeGoals(2);
		matchBean.setHalfTimeAwayGoals(1);
		
		matchBean.setMatchDate(new Date());
		
		
		
		_1X2OddsBean betBean = new _1X2OddsBean("1.1","2.1","3.1");
		betBean.setBetHouseString("WilliamHill");
		betBean.setTimeTypeString("_1");
		betBean = betDao.save(betBean);
		
		return betBean;
		
		
	}
	
}
