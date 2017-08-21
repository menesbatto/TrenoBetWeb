package app.dao.tabelle;

import static org.mockito.Matchers.matches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.EhOdds;
import app.dao.tabelle.entities.IBet;
import app.dao.tabelle.entities.Matcho;
import app.dao.tabelle.entities.Team;
import app.dao.tabelle.entities.UoOdds;
import app.dao.tabelle.entities._1X2Odds;
import app.dao.tipologiche.BetHouseDao;
import app.dao.tipologiche.HomeVariationTypeDao;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.UoThresholdTypeDao;
import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.HomeVariationType;
import app.dao.tipologiche.entities.TimeType;
import app.dao.tipologiche.entities.UoThresholdType;
import app.logic.app._0_eventsOddsDownloader.model.EhOddsBean;
import app.logic.app._0_eventsOddsDownloader.model.IBetBean;
import app.logic.app._0_eventsOddsDownloader.model.MatchBean;
import app.logic.app._0_eventsOddsDownloader.model.UoOddsBean;
import app.logic.app._0_eventsOddsDownloader.model._1X2OddsBean;
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
	
	@Autowired
	private HomeVariationTypeDao homeVariationTypeDao;
	
	@Autowired
	private UoThresholdTypeDao uoThresholdTypeDao;
	
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
		
		List<_1X2Odds> _1x2oddsEnt = matchEnt.get_1X2();
		List<_1X2OddsBean> _1x2oddsBean = matchBean.get_1X2();
		
		for (int i = 0; i < _1x2oddsBean.size(); i++) {
			_1X2OddsBean oddsBean = _1x2oddsBean.get(i);
			_1X2Odds oddsEnt = _1x2oddsEnt.get(i);

			populateOddsCommonFields(oddsBean, oddsEnt);
			
		} 
		
		List<EhOdds> ehOddsEnt = matchEnt.getEh();
		List<EhOddsBean> ehOddsBean = matchBean.getEh();
		
		for (int i = 0; i < ehOddsBean.size(); i++) {
			EhOddsBean oddsBean = ehOddsBean.get(i);
			EhOdds oddsEnt = ehOddsEnt.get(i);

			populateOddsCommonFields(oddsBean, oddsEnt);
			
			HomeVariationType homeVariationTypeEnt = homeVariationTypeDao.findByValue(oddsBean.getHomeVariationTypeString());
			oddsEnt.setHomeVariationType(homeVariationTypeEnt);
			
		} 
		
		List<UoOdds> uoOddsEnt = matchEnt.getUo();
		List<UoOddsBean> uoOddsBean = matchBean.getUo();
		
		for (int i = 0; i < _1x2oddsBean.size(); i++) {
			UoOddsBean oddsBean = uoOddsBean.get(i);
			UoOdds oddsEnt = uoOddsEnt.get(i);

			populateOddsCommonFields(oddsBean, oddsEnt);
			
			UoThresholdType uoThresholdTypeEnt = uoThresholdTypeDao.findByValue(oddsBean.getThresholdTypeString());
			oddsEnt.setThresholdType(uoThresholdTypeEnt);
			
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

	private void populateOddsCommonFields(IBetBean oddsBean, IBet oddsEnt) {
		BetHouse betHouseEnt = betHouseDao.findByValue(oddsBean.getBetHouseString());
		oddsEnt.setBetHouse(betHouseEnt);
		
		TimeType timeTypeEnt = timeTypeDao.findByValue(oddsBean.getTimeTypeString());
		oddsEnt.setTimeType(timeTypeEnt);
	}
	
	private void initTimeTypeMap() {
//		timeTypeMap = new HashMap<String, TimeType>();
//		for (Iterator<TimeType> iter = findAll.iterator(); iter.hasNext(); ) {
//			TimeType element = iter.next();
//			timeTypeMap.put(element.getValue(), element);
//		}	
	}
	
}
