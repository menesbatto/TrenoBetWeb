package app.dao;

import static org.mockito.Matchers.matches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app._0_eventsOddsDownloader.model.MatchBean;
import app._0_eventsOddsDownloader.model._1X2OddsBean;
import app.dao.entities.BetHouse;
import app.dao.entities.Champ;
import app.dao.entities.Matcho;
import app.dao.entities.Team;
import app.dao.entities.TimeType;
import app.dao.entities._1X2Odds;
import ma.glasnost.orika.MapperFacade;

@Service
public class MatchoDao {

	@Autowired
	private MapperFacade mapper;
	
	@Autowired
	private MatchoRepo matchRepo;

	@Autowired
	private TeamDao teamDao;

	@Autowired
	private ChampDao champDao;

	@Autowired
	private BetHouseDao betHouseDao;

	@Autowired
	private TimeTypeDao timeTypeDao;
	
//	private HashMap<String, TimeType> timeTypeMap;
	
//	public Champ findByNameAndStartYearAndNation(String name, int startYear, String nation) {
//		List<Champ> findAll = champRepo.findByNameAndStartYearAndNation(name, startYear, nation);
//		Champ first = findAll.get(0);
//			
//		return first;
//	}

	public void initTable() {
		
	}
	
	public MatchBean save(MatchBean matchBean) {
		Matcho matchEnt = new Matcho();
		mapper.map(matchBean, matchEnt);
		
		List<_1X2Odds> _1x2oddsEnt = matchEnt.get_1X2odds();
		List<_1X2OddsBean> _1x2oddsBean = matchBean.get_1X2odds();
		
		for (int i = 0; i < _1x2oddsBean.size(); i++) {
			_1X2OddsBean oddsBean = _1x2oddsBean.get(i);
			_1X2Odds oddsEnt = _1x2oddsEnt.get(i);

			BetHouse betHouseEnt = betHouseDao.findByValue(oddsBean.getBetHouseString());
			oddsEnt.setBetHouse(betHouseEnt);
			
			TimeType timeTypeEnt = timeTypeDao.findByValue(oddsBean.getTimeTypeString());
			oddsEnt.setTimeType(timeTypeEnt);
			
		} 
		
		Team homeTeam = teamDao.findByName(matchBean.getHomeTeam());
		matchEnt.setHomeTeam(homeTeam);

		Team awayTeam = teamDao.findByName(matchBean.getAwayTeam());
		matchEnt.setAwayTeam(awayTeam);
		
		Champ champ = champDao.findByNameAndStartYearAndNation(matchBean.getChamp());
		matchEnt.setChamp(champ);
		
		matchRepo.save(matchEnt);

		mapper.map(matchEnt, matchBean);
		return matchBean;
		
	}
	
	private void initTimeTypeMap() {
//		timeTypeMap = new HashMap<String, TimeType>();
//		for (Iterator<TimeType> iter = findAll.iterator(); iter.hasNext(); ) {
//			TimeType element = iter.next();
//			timeTypeMap.put(element.getValue(), element);
//		}	
	}
	
}
