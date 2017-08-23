package app.logic._1_matchResultParser;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.MatchoDao;
import app.logic._1_matchResultParser.modelNew.ChampBean;
import app.logic._1_matchResultParser.modelNew.EhOddsBean;
import app.logic._1_matchResultParser.modelNew.MatchBean;
import app.logic._1_matchResultParser.modelNew.TeamBean;
import app.logic._1_matchResultParser.modelNew.UoOddsBean;
import app.logic._1_matchResultParser.modelNew._1X2OddsBean;

@Service
public class ResultParserNewModel {



	@Autowired
	private MatchoDao matchDao;
	
	
	
	
	public _1X2OddsBean execute() {
		MatchBean matchBean = new MatchBean();
		ChampBean champ = new ChampBean();
		champ.setName("Serie A");
		champ.setStartYear(2017);
		champ.setNation("Italy");
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
//		betBean = betDao.save(betBean);

		_1X2OddsBean betBean2 = new _1X2OddsBean("1.3","2.3","3.3");
		betBean2.setBetHouseString("WilliamHill");
		betBean2.setTimeTypeString("_2");
//		betBean2 = betDao.save(betBean);

		List<_1X2OddsBean> _1x2oddsList = new ArrayList<_1X2OddsBean>();
		_1x2oddsList.add(betBean);
		_1x2oddsList.add(betBean2);
		matchBean.set_1X2(_1x2oddsList);
		
		
		
		
		EhOddsBean betBean3 = new EhOddsBean("2.1","3.1","4.1");
		betBean3.setBetHouseString("WilliamHill");
		betBean3.setTimeTypeString("_1");
		betBean3.setHomeVariationTypeString("m1");
		
		EhOddsBean betBean4 = new EhOddsBean("3.3","4.3","5.3");
		betBean4.setBetHouseString("WilliamHill");
		betBean4.setTimeTypeString("_2");
		betBean4.setHomeVariationTypeString("m2");
		
		List<EhOddsBean> ehOddsList = new ArrayList<EhOddsBean>();
		ehOddsList.add(betBean3);
		ehOddsList.add(betBean4);
		matchBean.setEh(ehOddsList);

		
		
		
		UoOddsBean betBean5 = new UoOddsBean("1.1","2.9");
		betBean5.setBetHouseString("WilliamHill");
		betBean5.setTimeTypeString("_1");
		betBean5.setThresholdTypeString("_1_5");
//		betBean = betDao.save(betBean);
		
		UoOddsBean betBean6 = new UoOddsBean("1.3","2.3");
		betBean6.setBetHouseString("WilliamHill");
		betBean6.setTimeTypeString("_2");
		betBean6.setThresholdTypeString("_2_5");
//		betBean2 = betDao.save(betBean);
		
		
		List<UoOddsBean> uoOddsList = new ArrayList<UoOddsBean>();
		uoOddsList.add(betBean5);
		uoOddsList.add(betBean6);
		matchBean.setUo(uoOddsList);
		
		
		
		matchDao.save(matchBean);
		
		
		return betBean;
		
		
	}
	
}
