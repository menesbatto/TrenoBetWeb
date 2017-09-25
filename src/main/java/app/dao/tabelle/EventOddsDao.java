package app.dao.tabelle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.EventOdds;
import app.dao.tabelle.entities.GoalsStats;
import app.dao.tabelle.entities.Matcho;
import app.dao.tabelle.entities.ResultGoodness;
import app.dao.tabelle.entities.ResultGoodnessUo;
import app.dao.tabelle.entities.ResultGoodnessWdl;
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
import app.logic._1_matchesDownlaoder.model.ResultGoodnessUoBean;
import app.logic._1_matchesDownlaoder.model.ResultGoodnessWDLBean;
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
		EventOdds ent;
		
		List<Matcho> matches = new ArrayList<Matcho>();
		
		for (Entry<TimeTypeEnum, ArrayList<EventOddsBean>> entry : eventsOddsBeanMap.entrySet()) {
			TimeTypeEnum timeTypeBean = entry.getKey();
			ArrayList<EventOddsBean> eventsOddsBean = entry.getValue();
			for (EventOddsBean bean : eventsOddsBean) {
				ent = new EventOdds();
				
				ent.setHomeMotivation(bean.getHomeMotivation());
				ent.setAwayMotivation(bean.getAwayMotivation());
				ent.setHomeTrend(bean.getHomeTrend());
				ent.setAwayTrend(bean.getAwayTrend());
				
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
				
				Matcho match =  findMatch(bean.getHomeTeam(), bean.getAwayTeam(), champEnum, matches);
				ent.setMatch(match);
				
				match.getEventsOdds().add(ent);
					
			
			}
		}
		
		matchDao.saveAll(matches);
		
		
	}

	
	private Matcho findMatch( String homeTeam, String awayTeam, ChampEnum champEnum, List<Matcho> matches) {
		for (Matcho m : matches)
			if (m.getHomeTeam().getName().equals(homeTeam))
				if (m.getAwayTeam().getName().equals(awayTeam))
					return m;
			
		Matcho match = matchDao.findByTeamAndChamp(homeTeam, awayTeam, champEnum);
		match.setEventsOdds(new ArrayList<EventOdds>());
		matches.add(match);
		return match;
	}
	

	private ResultGoodness createResultGoodness(ResultGoodnessBean bean) {
		ResultGoodness ent = new ResultGoodness();
		List<ResultGoodnessWdl> eh = createResultGoodnessEh(bean.getEhGoodness());
		ent.setEh(eh);
		
		List<ResultGoodnessUo> uo = createResultGoodnessUo(bean.getUoGoodness());
		ent.setUo(uo);
		
//		EventOdds eventOdds = 
//		ent.setEventOdds(eventOdds);
		
		ResultGoodnessWdl winClean = createResultGoodnessWdlEnt(bean.getWinClean());
		ent.setWinClean(winClean);

		ResultGoodnessWdl winFinal = createResultGoodnessWdlEnt(bean.getWinFinal());
		ent.setWinFinal(winFinal);
		
		ResultGoodnessWdl winTrend = createResultGoodnessWdlEnt(bean.getWinTrend());
		ent.setWinTrend(winTrend);
		
		ResultGoodnessWdl winMotivation = createResultGoodnessWdlEnt(bean.getWinMotivation());
		ent.setWinMotivation(winMotivation);
		
		return ent;
	}

	private List<ResultGoodnessWdl> createResultGoodnessEh(Map<HomeVariationEnum, ResultGoodnessWDLBean> mapBean) {
		List<ResultGoodnessWdl> listEnt = new ArrayList<ResultGoodnessWdl>();
		if (mapBean!= null) {
			ResultGoodnessWdl ent;
			for (Entry<HomeVariationEnum, ResultGoodnessWDLBean> entry : mapBean.entrySet()) {
				ResultGoodnessWDLBean bean = entry.getValue();
				
				ent = createResultGoodnessWdlEnt(bean);
				
				listEnt.add(ent);
			}
		}
			
		return listEnt;
	}

	private List<ResultGoodnessUo> createResultGoodnessUo(Map<UoThresholdEnum, ResultGoodnessUoBean> mapBean) {
		List<ResultGoodnessUo> listEnt = new ArrayList<ResultGoodnessUo>();
		if (mapBean!= null) {
			ResultGoodnessUo ent;
			for (Entry<UoThresholdEnum, ResultGoodnessUoBean> entry : mapBean.entrySet()) {
				ResultGoodnessUoBean bean = entry.getValue();
				
				ent = new ResultGoodnessUo();
				ent.setGoodnessU(bean.getGoodnessU());
				ent.setGoodnessO(bean.getGoodnessO());
				UoThresholdEnum key = entry.getKey();
				UoThresholdType thresholdEnt = uoThresholdTypeDao.findByValue(key.name());
				ent.setThreshold(thresholdEnt);
				
				listEnt.add(ent);
			}
		}
			
		return listEnt;
	}

	private ResultGoodnessWdl createResultGoodnessWdlEnt(ResultGoodnessWDLBean bean) {
		ResultGoodnessWdl ent = new ResultGoodnessWdl();
		if (bean != null) {
			ent.setGoodnessW(bean.getGoodnessW());
			ent.setGoodnessD(bean.getGoodnessD());
			ent.setGoodnessL(bean.getGoodnessL());
			
			if (bean.getHomeVariationType() != null) {
				HomeVariationEnum homeVariationEnum = bean.getHomeVariationType();
				HomeVariationType homeVariationType = homeVariationTypeDao.findByValue(homeVariationEnum.name());
				ent.setHomeVariationType(homeVariationType);
		
			}
		}
		
		return ent;
	}

	private Map<UoThresholdType, String> createTrendUoMap(Map<UoThresholdEnum, String> beanMap) {
		Map<UoThresholdType, String> entMap = new HashMap<UoThresholdType, String>();
		if (beanMap != null) {
			for (Entry<UoThresholdEnum, String> entry : beanMap.entrySet()) {
				UoThresholdEnum key = entry.getKey();
				UoThresholdType homVarEnt = uoThresholdTypeDao.findByValue(key.name());
				String value = entry.getValue();
				entMap.put(homVarEnt,value);
			}
		}
		
		
		
		return entMap;
	}

	private Map<HomeVariationType, String> createTrendEhMap(Map<HomeVariationEnum, String> beanMap) {
		Map<HomeVariationType, String> entMap = new HashMap<HomeVariationType, String>();
		if (beanMap!= null) {
			for (Entry<HomeVariationEnum, String> entry : beanMap.entrySet()) {
				HomeVariationEnum key = entry.getKey();
				HomeVariationType homVarEnt = homeVariationTypeDao.findByValue(key.name());
				String value = entry.getValue();
				entMap.put(homVarEnt,value);
			}
		}
		
		return entMap;
	}

}
