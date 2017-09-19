package app.dao.tabelle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.GoalsStats;
import app.dao.tabelle.entities.Team;
import app.dao.tabelle.entities.WinRangeStats;
import app.dao.tipologiche.OddsRangeDao;
import app.dao.tipologiche.OddsRangeRepo;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.UoThresholdTypeDao;
import app.dao.tipologiche.entities.OddsRange;
import app.dao.tipologiche.entities.TimeType;
import app.dao.tipologiche.entities.UoThresholdType;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;
import app.logic._1_matchesDownlaoder.model.UoThresholdEnum;
import app.logic._1_matchesDownlaoder.modelNew.TeamBean;
import app.logic._2_matchResultAnalyzer.model.GoalsStatsBean;
import app.logic._2_matchResultAnalyzer.model.UoThresholdStats;
import app.logic._2_matchResultAnalyzer.model.WinRangeStatsBean;
import app.utils.ChampEnum;
import ma.glasnost.orika.MapperFacade;

@Service
public class GoalsStatsDao {

	@Autowired
	private GoalsStatsRepo goalsStatsRepo;

	@Autowired
	private UoThresholdTypeDao uoThresholdTypeDao;

	@Autowired
	private TeamDao teamDao;

	@Autowired
	private ChampDao champDao;

	@Autowired
	private TimeTypeDao timeTypeDao;

	@Autowired
	private MapperFacade mapper;
	
	public List<GoalsStatsBean> findByChamp(ChampEnum champEnum) {
		Champ champ = champDao.findByChampEnum(champEnum);
		List<GoalsStats> ents = goalsStatsRepo.findByTeamChampOrderByTeam(champ);
		List<GoalsStatsBean> beans = new ArrayList<GoalsStatsBean>();
		GoalsStatsBean bean;
		for (GoalsStats ent : ents) {
			bean = getBeanFromList(ent.getTeamName(), ent.getPlayingField(), ent.getTimeType(), beans);
			if (bean == null) {
				bean = new GoalsStatsBean();
				mapper.map(ent, bean);
				bean.setTeamName(ent.getTeam().getName());
				TimeTypeEnum timeTypeBean = timeTypeDao.findBeanByEnt(ent.getTimeType());
				bean.setTimeTypeBean(timeTypeBean);
				bean.setPlayingField(ent.getPlayingField());
				beans.add(bean);
			}
			
			UoThresholdEnum thrBean = uoThresholdTypeDao.findBeanByEnt(ent.getThreshold());
			UoThresholdStats thrStatsBean = new UoThresholdStats();
			thrStatsBean.setOverHit(ent.getOverHit());
			thrStatsBean.setOverPerc(ent.getOverPerc());
			thrStatsBean.setUnderHit(ent.getUnderHit());
			thrStatsBean.setUnderPerc(ent.getUnderPerc());
			bean.getThresholdMap().put(thrBean, thrStatsBean);
			
		}
		return beans;
		
	}
	
//	public GoalsStatsBean findByTeamNameAndChampAndTimeTypeAndPlayingField(String teamName, ChampEnum champEnum,	TimeTypeEnum timeTypeEnum, String playingField) {
//	
//		Champ champ = champDao.findByChampEnum(champEnum);
//		Team team = teamDao.findByNameAndChamp(teamName, champ);
//		TimeType timeType = timeTypeDao.findByValue(timeTypeEnum.name());
//		List<GoalsStats> listEnt = goalsStatsRepo.findByTeamAndTimeTypeAndPlayingField(team, timeType, playingField);
//		if (listEnt.isEmpty()){
//			listEnt = initGoalsStatsForTeam(team, timeType, playingField);
//		}
//		
//		
//		GoalsStatsBean bean = new GoalsStatsBean();
//		for (GoalsStats ent : listEnt) {
//			if (bean.getTeamName() == null) {	//Lo mappo solo la prima volta
//				mapper.map(ent, bean);
//				bean.setTeamName(ent.getTeam().getName());
//			}
//			else {
//				UoThresholdEnum findBeanByEnt = uoThresholdTypeDao.findBeanByEnt(ent.getThreshold());
//				UoThresholdStats uoThresholdStats = bean.getThresholdMap().get(findBeanByEnt);
//				uoThresholdStats.setOverHit(ent.getOverHit());
//				uoThresholdStats.setOverPerc(ent.getOverPerc());
//				uoThresholdStats.setUnderHit(ent.getUnderHit());
//				uoThresholdStats.setUnderPerc(ent.getUnderPerc());
//			}
//			
//			
//		}
//
//		return bean;
//	}
//	public ArrayList<GoalsStatsBean> findByTeamNameAndChampAndTimeTypeAndPlayingField(String teamName, ChampEnum champEnum,	TimeTypeEnum timeTypeEnum, String playingField) {
//		
//		Champ champ = champDao.findByChampEnum(champEnum);
//		Team team = teamDao.findByNameAndChamp(teamName, champ);
//		TimeType timeType = timeTypeDao.findByValue(timeTypeEnum.name());
//		List<GoalsStats> listEnt = goalsStatsRepo.findByTeamAndTimeTypeAndPlayingField(team, timeType, playingField);
//		if (listEnt.isEmpty()){
//			listEnt = initGoalsStatsForTeam(team, timeType, playingField);
//		}
//		
//		
//		ArrayList<GoalsStatsBean> listBean = new ArrayList<GoalsStatsBean>();
//		for (GoalsStats ent : listEnt) {
//			GoalsStatsBean bean = new GoalsStatsBean();
//			if (ent.getTotalMatches() != null)	// Per non sovrascrivere gli 0 con i null provenienti da DB, in caso metti diretto gli 0 a DB
//				mapper.map(ent, bean);
//			
//			
//			bean.setThreshold(ent.getThreshold().getValueNum());
//			bean.setTeamName(ent.getTeam().getName());
//			
//			listBean.add(bean);
//		}
//		
//		return listBean;
//	}
	

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

	
	public List<GoalsStatsBean> saveGoalsStats(ArrayList<GoalsStatsBean> beans, String teamName, ChampEnum champEnum, String playingField) {
		Champ champ = champDao.findByChampEnum(champEnum);
		
		Team teamEnt = teamDao.findByNameAndChamp(teamName, champ);

		
		List<GoalsStats> existingGoalsStats = goalsStatsRepo.findByTeamAndPlayingField(teamEnt, playingField);

		for (GoalsStatsBean bean : beans) {
			
			TimeType beanTimeType = timeTypeDao.findByValue(bean.getTimeTypeBean().name());
			
			if (existingGoalsStats.isEmpty()){ // ci entra soltanto quando viene creata la statTotal (indipendente dal tempo) 
				existingGoalsStats = initGoalsStatsForTeam(teamEnt, playingField);
			}
			
			for (GoalsStats ent : existingGoalsStats) {
				for ( Entry<UoThresholdEnum, UoThresholdStats> entry : bean.getThresholdMap().entrySet()) {
					UoThresholdEnum key = entry.getKey();
					if (key.getValueNum().equals(ent.getThreshold().getValueNum())) {
						if (beanTimeType.equals(ent.getTimeType())) {
							UoThresholdStats value = entry.getValue();
							mapper.map(bean, ent);
							ent.setPlayingField(playingField);	//playingField perche se lo perde col map...cci sua
							ent.setTimeType(beanTimeType);
							ent.setOverHit(value.getOverHit());
							ent.setOverPerc(value.getOverPerc());
							ent.setUnderHit(value.getUnderHit());
							ent.setUnderPerc(value.getUnderPerc());
						}
					}
				}
			}
			
		}

		goalsStatsRepo.save(existingGoalsStats);
		
		return beans;
	}
//	public List<GoalsStats> saveWinRangeStats(List<GoalsStatsBean> listBean, String teamName, ChampEnum champEnum, TimeTypeEnum timeTypeEnum, String playingField) {
//		Champ champ = champDao.findByChampEnum(champEnum);
//		
//		Team teamEnt = teamDao.findByNameAndChamp(teamName, champ);
//		
//		TimeType timeType = timeTypeDao.findByValue(timeTypeEnum.name());
//		
//		List<GoalsStats> existingGoalsStats = goalsStatsRepo.findByTeamAndTimeTypeAndPlayingField(teamEnt,timeType, playingField);
//		
//		for (GoalsStats ent : existingGoalsStats) {
//			for (GoalsStatsBean bean : listBean) {
//				if (bean.getThreshold().equals(ent.getThreshold().getValueNum())) {
//					mapper.map(bean, ent);
//				}
//			}
//		}
//		
//		goalsStatsRepo.save(existingGoalsStats);
//		
//		return existingGoalsStats;
//	}



	public void calculateGoalsStatsNoPlayingField(String teamName, ChampEnum champEnum, List<GoalsStatsBean> homeStatses, List<GoalsStatsBean> awayStatses) {
		
		ArrayList<GoalsStatsBean> totalStatses = new ArrayList<GoalsStatsBean>();
//		if (homeStatses == null)
//			homeStatses = initGoalsStatsForTeam(team, timeType, playingField)
//		else if (awayStatses == null)
//			awayStatses = new ArrayList<GoalsStatsBean>();
			
//		for (TimeTypeEnum timeTypeEnum : timeTypeDao.findAllTimeTypeEnum()) {
		for (int i = 0; i < 3; i++) {

//			GoalsStatsBean homeStats = findByTeamNameAndChampAndTimeTypeAndPlayingField(teamName, champEnum, timeTypeEnum, "A");
//			GoalsStatsBean awayStats = findByTeamNameAndChampAndTimeTypeAndPlayingField(teamName, champEnum, timeTypeEnum, "H");
			GoalsStatsBean homeStats = !homeStatses.isEmpty() ? homeStatses.get(i) : createEmptyOne(awayStatses.get(i));
			GoalsStatsBean awayStats = !awayStatses.isEmpty() ? awayStatses.get(i) : createEmptyOne(homeStatses.get(i));

			GoalsStatsBean totalStats = new GoalsStatsBean();
			
			for (Entry<UoThresholdEnum, UoThresholdStats> entryH : homeStats.getThresholdMap().entrySet()) {
				if (totalStats.getTeamName() == null) {
					totalStats.setTeamName(homeStats.getTeamName());
					totalStats.setTimeTypeBean(homeStats.getTimeTypeBean());
					totalStats.setTotalMatches(homeStats.getTotalMatches() + awayStats.getTotalMatches());
					totalStats.setTotalGoals(homeStats.getTotalGoals() +  awayStats.getTotalGoals());
					totalStats.setTakenGoalsTotal(homeStats.getTakenGoalsTotal() + awayStats.getTakenGoalsTotal());
					totalStats.setStrikedGoalsTotal(homeStats.getStrikedGoalsTotal() + awayStats.getStrikedGoalsTotal());
				}
				UoThresholdEnum keyH = entryH.getKey();
				for (Entry<UoThresholdEnum, UoThresholdStats> entryA : awayStats.getThresholdMap().entrySet()) {
					UoThresholdEnum keyA = entryA.getKey();
					if (keyH.equals(keyA)) {
						UoThresholdStats valueH = entryH.getValue();
						UoThresholdStats valueA = entryA.getValue();
						Integer totalMatches = homeStats.getTotalMatches() + awayStats.getTotalMatches();
						
						Double underHit = valueH.getUnderHit() + valueA.getUnderHit();
						Double overHit = valueH.getOverHit() + valueA.getOverHit();
						
						Double underPerc = 0.0;
						Double overPerc = 0.0;
						if (totalMatches != 0) {
							underPerc = underHit / totalMatches.doubleValue();
							overPerc = overHit / totalMatches.doubleValue();
						}
						UoThresholdStats valueF = new UoThresholdStats(underHit, underPerc, overHit, overPerc); 
						totalStats.getThresholdMap().put(keyA, valueF);
					}
				}
			}
			totalStatses.add(totalStats);
		}
		
		saveGoalsStats(totalStatses, teamName, champEnum, "T");

	}


	private GoalsStatsBean createEmptyOne(GoalsStatsBean goalsStatsBean) {
		GoalsStatsBean newOne = new GoalsStatsBean();
		
		newOne.setTimeTypeBean(goalsStatsBean.getTimeTypeBean());
		newOne.setTeamName(goalsStatsBean.getTeamName());
		return newOne;
	}



	public int getLastSeasonDayOddsAndPlayingField(String teamName, ChampEnum champEnum, String playingField) {
		Champ champ = champDao.findByChampEnum(champEnum);
		
		Team teamEnt = teamDao.findByNameAndChamp(teamName, champ);

		GoalsStats goalsStats = goalsStatsRepo.findFirstByTeamAndPlayingField(teamEnt, playingField);
		Integer totalMatches = 0;
		if (goalsStats!= null)
			totalMatches = goalsStats.getTotalMatches();
		
		return totalMatches;
	}
	
	
	
	

}
