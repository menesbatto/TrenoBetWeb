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
	
	public GoalsStatsBean findByTeamNameAndChampAndTimeTypeAndPlayingField(String teamName, ChampEnum champEnum,	TimeTypeEnum timeTypeEnum, String playingField) {
	
		Champ champ = champDao.findByChampEnum(champEnum);
		Team team = teamDao.findByNameAndChamp(teamName, champ);
		TimeType timeType = timeTypeDao.findByValue(timeTypeEnum.name());
		List<GoalsStats> listEnt = goalsStatsRepo.findByTeamAndTimeTypeAndPlayingField(team, timeType, playingField);
		if (listEnt.isEmpty()){
			listEnt = initGoalsStatsForTeam(team, timeType, playingField);
		}
		
		
		GoalsStatsBean bean = new GoalsStatsBean();
		for (GoalsStats ent : listEnt) {
			if (bean.getTeamName() == null) {	//Lo mappo solo la prima volta
				mapper.map(ent, bean);
				bean.setTeamName(ent.getTeam().getName());
			}
			else {
				UoThresholdEnum findBeanByEnt = uoThresholdTypeDao.findBeanByEnt(ent.getThreshold());
				UoThresholdStats uoThresholdStats = bean.getThresholdMap().get(findBeanByEnt);
				uoThresholdStats.setOverHit(ent.getOverHit());
				uoThresholdStats.setOverPerc(ent.getOverPerc());
				uoThresholdStats.setUnderHit(ent.getUnderHit());
				uoThresholdStats.setUnderPerc(ent.getUnderPerc());
			}
			
			
		}

		return bean;
	}
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
	

	public List<GoalsStats> initGoalsStatsForTeam(Team team, TimeType timeType, String playingField) {
		List<GoalsStats> goalsStatsList = new ArrayList<GoalsStats>();
		List<UoThresholdType> thresholdList = uoThresholdTypeDao.findAll();
		initSingleGoalsStatsForTeam(team, goalsStatsList, thresholdList, playingField,  timeType);
		goalsStatsRepo.save(goalsStatsList);
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

	
	public List<GoalsStats> saveGoalsStats(GoalsStatsBean bean, String teamName, ChampEnum champEnum, TimeTypeEnum timeTypeEnum, String playingField) {
		Champ champ = champDao.findByChampEnum(champEnum);
		
		Team teamEnt = teamDao.findByNameAndChamp(teamName, champ);

		TimeType timeType = timeTypeDao.findByValue(timeTypeEnum.name());
		
		List<GoalsStats> existingGoalsStats = goalsStatsRepo.findByTeamAndTimeTypeAndPlayingField(teamEnt,timeType, playingField);

		for (GoalsStats ent : existingGoalsStats) {
			for ( Entry<UoThresholdEnum, UoThresholdStats> entry : bean.getThresholdMap().entrySet()) {
				UoThresholdEnum key = entry.getKey();
				if (key.getValueNum().equals(ent.getThreshold().getValueNum())) {
					UoThresholdStats value = entry.getValue();
					mapper.map(bean, ent);
					ent.setOverHit(value.getOverHit());
					ent.setOverPerc(value.getOverPerc());
					ent.setUnderHit(value.getUnderHit());
					ent.setUnderPerc(value.getUnderPerc());
				}
			}
		}
		
		goalsStatsRepo.save(existingGoalsStats);
		
		return existingGoalsStats;
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



	public void calculateWinStatsNoPlayingField(String teamName, ChampEnum champEnum) {
		for (TimeTypeEnum timeTypeEnum : timeTypeDao.findAllTimeTypeEnum()) {
			GoalsStatsBean homeStats = findByTeamNameAndChampAndTimeTypeAndPlayingField(teamName, champEnum, timeTypeEnum, "A");
			GoalsStatsBean awayStats = findByTeamNameAndChampAndTimeTypeAndPlayingField(teamName, champEnum, timeTypeEnum, "H");

			GoalsStatsBean totalStats = new GoalsStatsBean();

			
			for (Entry<UoThresholdEnum, UoThresholdStats> entryH : homeStats.getThresholdMap().entrySet()) {
				if (totalStats.getTeamName() == null)
					mapper.map(entryH, totalStats);
				UoThresholdEnum keyH = entryH.getKey();
				for (Entry<UoThresholdEnum, UoThresholdStats> entryA : awayStats.getThresholdMap().entrySet()) {
					UoThresholdEnum keyA = entryA.getKey();
					if (keyH.equals(keyH)) {
						UoThresholdStats valueH = entryH.getValue();
						UoThresholdStats valueA = entryA.getValue();
						Integer totalMatches = homeStats.getTotalMatches() + awayStats.getTotalMatches();
						
						Double underHit = valueH.getUnderHit() + valueA.getUnderHit();
						Double underPerc = underHit / totalMatches.doubleValue();
						Double overHit = valueH.getOverHit() + valueA.getOverHit();
						Double overPerc = overHit / totalMatches.doubleValue();
						UoThresholdStats valueF = new UoThresholdStats(underHit, underPerc, overHit, overPerc); 
						totalStats.getThresholdMap().put(keyA, valueF);
					}
				}
			}
			saveGoalsStats(totalStats, teamName, champEnum, timeTypeEnum, "T");
		}	

	}
	
	
	
	

}
