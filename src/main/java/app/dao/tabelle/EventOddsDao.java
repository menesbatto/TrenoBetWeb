package app.dao.tabelle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.EventOdds;
import app.dao.tabelle.entities.GoalsStats;
import app.dao.tabelle.entities.Matcho;
import app.dao.tabelle.entities.ResultGoodness;
import app.dao.tabelle.entities.Team;
import app.dao.tabelle.entities.WinRangeStats;
import app.dao.tipologiche.HomeVariationTypeDao;
import app.dao.tipologiche.OddsRangeDao;
import app.dao.tipologiche.OddsRangeRepo;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.UoThresholdTypeDao;
import app.dao.tipologiche.entities.HomeVariationType;
import app.dao.tipologiche.entities.OddsRange;
import app.dao.tipologiche.entities.TimeType;
import app.dao.tipologiche.entities.UoThresholdType;
import app.logic._1_matchesDownlaoder.model.EventOddsBean;
import app.logic._1_matchesDownlaoder.model.HomeVariationEnum;
import app.logic._1_matchesDownlaoder.model.ResultGoodnessBean;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;
import app.logic._1_matchesDownlaoder.model.UoThresholdEnum;
import app.logic._1_matchesDownlaoder.modelNew.TeamBean;
import app.logic._2_matchResultAnalyzer.model.GoalsStatsBean;
import app.logic._2_matchResultAnalyzer.model.UoThresholdStats;
import app.logic._2_matchResultAnalyzer.model.WinRangeStatsBean;
import app.utils.ChampEnum;
import ma.glasnost.orika.MapperFacade;

@Service
public class EventOddsDao {

	@Autowired
	private EventOddsRepo eventOddsRepo;

	@Autowired
	private UoThresholdTypeDao uoThresholdTypeDao;

	@Autowired
	private TeamDao teamDao;

	@Autowired
	private ChampDao champDao;

	@Autowired
	private TimeTypeDao timeTypeDao;

	@Autowired
	private MatchoDao matchDao;

	@Autowired
	private HomeVariationTypeDao homeVariationTypeDao;

	@Autowired
	private MapperFacade mapper;
	
	public List<EventOddsBean> findByChamp(ChampEnum champEnum) {
		Champ champ = champDao.findByChampEnum(champEnum);
		List<EventOdds> ents = eventOddsRepo.findByMatchChamp(champ);
		List<EventOddsBean> beans = new ArrayList<EventOddsBean>();
		EventOddsBean bean;
		for (EventOdds ent : ents) {
			
//			bean = getBeanFromList(ent.getTeamName(), ent.getPlayingField(), ent.getTimeType(), beans);
//			if (bean == null) {
//				bean = new GoalsStatsBean();
//				mapper.map(ent, bean);
//				bean.setTeamName(ent.getTeam().getName());
//				TimeTypeEnum timeTypeBean = timeTypeDao.findBeanByEnt(ent.getTimeType());
//				bean.setTimeTypeBean(timeTypeBean);
//				bean.setPlayingField(ent.getPlayingField());
//				beans.add(bean);
//			}
//			
//			UoThresholdEnum thrBean = uoThresholdTypeDao.findBeanByEnt(ent.getThreshold());
//			UoThresholdStats thrStatsBean = new UoThresholdStats();
//			thrStatsBean.setOverHit(ent.getOverHit());
//			thrStatsBean.setOverPerc(ent.getOverPerc());
//			thrStatsBean.setUnderHit(ent.getUnderHit());
//			thrStatsBean.setUnderPerc(ent.getUnderPerc());
//			bean.getThresholdMap().put(thrBean, thrStatsBean);
			
		}
		return beans;
		
	}

	private GoalsStatsBean getBeanFromList(String teamName, String playingField, TimeType timeType, List<GoalsStatsBean> beans) {
		for (GoalsStatsBean bean : beans) {
			if (bean.getTeamName().equals(teamName)) {
				if (bean.getPlayingField().equals(playingField)) {
					TimeTypeEnum timeTypeEnum = timeTypeDao.findBeanByEnt(timeType);
					if (bean.getTimeTypeBean().equals(timeTypeEnum)) {
						return bean;
					}
				}
			}
		}
		return null;
	}

	public List<GoalsStats> initGoalsStatsForTeam(Team team, String playingField) {
		List<GoalsStats> goalsStatsList = new ArrayList<GoalsStats>();
		List<UoThresholdType> thresholdList = uoThresholdTypeDao.findAll();
		List<TimeType> timeTypes = timeTypeDao.findAll();
		for (TimeType timeType : timeTypes) {
			initSingleGoalsStatsForTeam(team, goalsStatsList, thresholdList, playingField,  timeType);
		}
//		goalsStatsRepo.save(goalsStatsList);
		return goalsStatsList;
	}



	private void initSingleGoalsStatsForTeam(Team team, List<GoalsStats> goalsStatsList, List<UoThresholdType> thrs, String playingField, TimeType timeType) {
		for(UoThresholdType thr: thrs) {
			GoalsStats goalsStat = new GoalsStats(thr, team);
			goalsStat.setPlayingField(playingField);
			goalsStat.setTimeType(timeType);
			
			goalsStatsList.add(goalsStat);			
		}
	}

	public void save(Map<TimeTypeEnum, ArrayList<EventOddsBean>> eventsOddsBeanMap, ChampEnum champEnum) {
		List<EventOdds> eventsOddsEnt = new ArrayList<EventOdds>();
		EventOdds ent;
		
		for (Entry<TimeTypeEnum, ArrayList<EventOddsBean>> entry : eventsOddsBeanMap.entrySet()) {
			TimeTypeEnum timeTypeBean = entry.getKey();
			ArrayList<EventOddsBean> eventsOddsBean = entry.getValue();
			for (EventOddsBean bean : eventsOddsBean) {
				ent = new EventOdds();
				mapper.map(bean, ent);
				TimeType timeType = timeTypeDao.findByValue(timeTypeBean.name());
				ent.setTimeType(timeType);
				
				Map<HomeVariationType, String> homeTrendEh = createTrendEhMap(bean.getHomeTrendEh());
				ent.setHomeTrendEh(homeTrendEh);

				Map<HomeVariationType, String> awayTrendEh = createTrendEhMap(bean.getAwayTrendEh());;
				ent.setAwayTrendEh(awayTrendEh);
				
				Map<UoThresholdType, String> homeTrendUo = createTrendUoMap(bean.getHomeTrendUo());;
				ent.setHomeTrendUo(homeTrendUo);
				
				Map<UoThresholdType, String> awayTrendUo = createTrendUoMap(bean.getAwayTrendUo());;;
				ent.setAwayTrendUo(awayTrendUo);
				
				ResultGoodness homeResultGoodness = createResultGoodness(bean.getHomeResultGoodness());
				ent.setHomeResultGoodness(homeResultGoodness);

				ResultGoodness awayResultGoodness = createResultGoodness(bean.getHomeResultGoodness());
				ent.setAwayResultGoodness(awayResultGoodness);
				
				ResultGoodness totalResultGoodness = createResultGoodness(bean.getHomeResultGoodness());
				ent.setTotalResultGoodness(totalResultGoodness);
				
				//https://stackoverflow.com/questions/29061451/store-jpa-entity-with-given-field-object-id-instead-of-object-itself
				//Qui posso evitare di scaricarne uno per volta: cosi accedo 30  volte
				// se me li salvo ogni volta in una map accedo 10 volte
				// se faccio un for con tutti gli id all'inizio e faccio una find per id accedo 1 volta
				Matcho match =  matchDao.findByTeamAndChamp(bean.getHomeTeam(), bean.getAwayTeam(), champEnum);
				ent.setMatch(match);
			
			}
		}
		
	}
	

	
	
	private ResultGoodness createResultGoodness(ResultGoodnessBean bean) {
		ResultGoodness ent = new ResultGoodness();
		
		riprendi da qua
		return ent;
	}

	private Map<UoThresholdType, String> createTrendUoMap(Map<UoThresholdEnum, String> beanMap) {
		Map<UoThresholdType, String> entMap = new HashMap<UoThresholdType, String>();
		for (Entry<UoThresholdEnum, String> entry : beanMap.entrySet()) {
			UoThresholdEnum key = entry.getKey();
			UoThresholdType homVarEnt = uoThresholdTypeDao.findByValue(key.name());
			String value = entry.getValue();
			entMap.put(homVarEnt,value);
		}
		
		
		
		return entMap;
	}
	
	
	//torreinpietra 8 sabato

	private Map<HomeVariationType, String> createTrendEhMap(Map<HomeVariationEnum, String> beanMap) {
		Map<HomeVariationType, String> entMap = new HashMap<HomeVariationType, String>();
		for (Entry<HomeVariationEnum, String> entry : beanMap.entrySet()) {
			HomeVariationEnum key = entry.getKey();
			HomeVariationType homVarEnt = homeVariationTypeDao.findByValue(key.name());
			String value = entry.getValue();
			entMap.put(homVarEnt,value);
		}
		
		return entMap;
	}

	public List<GoalsStatsBean> saveGoalsStats(ArrayList<GoalsStatsBean> beans, String teamName, ChampEnum champEnum, String playingField) {
//		Champ champ = champDao.findByChampEnum(champEnum);
//		
//		Team teamEnt = teamDao.findByNameAndChamp(teamName, champ);
//
//		
//		List<GoalsStats> existingGoalsStats = goalsStatsRepo.findByTeamAndPlayingField(teamEnt, playingField);
//
//		for (GoalsStatsBean bean : beans) {
//			
//			TimeType beanTimeType = timeTypeDao.findByValue(bean.getTimeTypeBean().name());
//			
//			if (existingGoalsStats.isEmpty()){ // ci entra soltanto quando viene creata la statTotal (indipendente dal tempo) 
//				existingGoalsStats = initGoalsStatsForTeam(teamEnt, playingField);
//			}
//			
//			for (GoalsStats ent : existingGoalsStats) {
//				for ( Entry<UoThresholdEnum, UoThresholdStats> entry : bean.getThresholdMap().entrySet()) {
//					UoThresholdEnum key = entry.getKey();
//					if (key.getValueNum().equals(ent.getThreshold().getValueNum())) {
//						if (beanTimeType.equals(ent.getTimeType())) {
//							UoThresholdStats value = entry.getValue();
//							mapper.map(bean, ent);
//							ent.setPlayingField(playingField);	//playingField perche se lo perde col map...cci sua
//							ent.setTimeType(beanTimeType);
//							ent.setOverHit(value.getOverHit());
//							ent.setOverPerc(value.getOverPerc());
//							ent.setUnderHit(value.getUnderHit());
//							ent.setUnderPerc(value.getUnderPerc());
//						}
//					}
//				}
//			}
//			
//		}
//
//		goalsStatsRepo.save(existingGoalsStats);
//		
		return beans;
	}


	
	
	

}
