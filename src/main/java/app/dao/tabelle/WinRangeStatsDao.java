package app.dao.tabelle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.Team;
import app.dao.tabelle.entities.WinRangeStats;
import app.dao.tipologiche.OddsRangeDao;
import app.dao.tipologiche.OddsRangeRepo;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.entities.OddsRange;
import app.dao.tipologiche.entities.TimeType;
import app.logic._1_matchesDownlaoder.model.TimeTypeEnum;
import app.logic._1_matchesDownlaoder.modelNew.TeamBean;
import app.logic._2_matchResultAnalyzer.model.WinRangeStatsBean;
import app.utils.ChampEnum;
import ma.glasnost.orika.MapperFacade;

@Service
public class WinRangeStatsDao {

	@Autowired
	private WinRangeStatsRepo winRangeStatsRepo;

	@Autowired
	private OddsRangeDao oddsRangeDao;

	@Autowired
	private TeamDao teamDao;

	@Autowired
	private ChampDao champDao;

	@Autowired
	private TimeTypeDao timeTypeDao;

	@Autowired
	private MapperFacade mapper;
	
	public ArrayList<WinRangeStatsBean> findByTeamNameAndChampAndTimeTypeAndPlayingField(String teamName, ChampEnum champEnum, TimeTypeEnum timeTypeEnum, String playingField) {
		Champ champ = champDao.findByChampEnum(champEnum);
		Team team = teamDao.findByNameAndChamp(teamName, champ);
		TimeType timeType = timeTypeDao.findByValue(timeTypeEnum.name());
		List<WinRangeStats> listEnt = winRangeStatsRepo.findByTeamAndTimeTypeAndPlayingField(team, timeType, playingField);
		if (listEnt.isEmpty()){
			listEnt = initWinRangeStatsForTeam(team, timeType, playingField);
		}
		
		
		ArrayList<WinRangeStatsBean> listBean = new ArrayList<WinRangeStatsBean>();
		for (WinRangeStats ent : listEnt) {
			WinRangeStatsBean bean = new WinRangeStatsBean();
			if (ent.getTotal() != null)	// Per non sovrascrivere gli 0 con i null provenienti da DB, in caso metti diretto gli 0 a DB
				mapper.map(ent, bean);
			
			bean.setRange(ent.getRange().getValueDown() + "-" + ent.getRange().getValueUp());
			bean.setEdgeDown(ent.getRange().getValueDown());
			bean.setEdgeUp(ent.getRange().getValueUp());
			bean.setTeamName(ent.getTeam().getName());
			
			listBean.add(bean);
		}

		return listBean;
	}
	

	public List<WinRangeStats> initWinRangeStatsForTeam(Team team, TimeType timeType, String playingField) {
		List<WinRangeStats> winRangeStatsList = new ArrayList<WinRangeStats>();
		List<OddsRange> oddsRangeList = oddsRangeDao.findAll();
		initSingleWinRangeStatsForTeam(team, winRangeStatsList, oddsRangeList, playingField,  timeType);
		winRangeStatsRepo.save(winRangeStatsList);
		return winRangeStatsList;
	}



	private void initSingleWinRangeStatsForTeam(Team team, List<WinRangeStats> winRangeStatsList, List<OddsRange> oddsRangeList, String playingField, TimeType timeType) {
		for(OddsRange range: oddsRangeList) {
			WinRangeStats winRange = new WinRangeStats(range, team);
			winRange.setTimeType(timeType);
			winRange.setPlayingField(playingField);
			
			winRangeStatsList.add(winRange);			
		}
	}

	
	public List<WinRangeStats> saveWinRangeStats(List<WinRangeStatsBean> listBean, String teamName, ChampEnum champEnum, TimeTypeEnum timeTypeEnum, String playingField) {
		Champ champ = champDao.findByChampEnum(champEnum);
		
		Team teamEnt = teamDao.findByNameAndChamp(teamName, champ);

		TimeType timeType = timeTypeDao.findByValue(timeTypeEnum.name());
		
		List<WinRangeStats> existingWinRangeStats = winRangeStatsRepo.findByTeamAndTimeTypeAndPlayingField(teamEnt,timeType, playingField);
		
		if (existingWinRangeStats.isEmpty()){ // ci entra soltanto quando viene creata la statTotal (indipendente dal tempo) 
			existingWinRangeStats = initWinRangeStatsForTeam(teamEnt, timeType, playingField);
		}
		
		for (WinRangeStatsBean bean : listBean) {
			for (WinRangeStats ent : existingWinRangeStats) {
				if (bean.getEdgeUp().equals(ent.getRange().getValueUp())) {
					mapper.map(bean, ent);
				}
			}
		}
		
		winRangeStatsRepo.save(existingWinRangeStats);
		
		return existingWinRangeStats;
	}



	public void calculateWinStatsNoPlayingField(String teamName, ChampEnum champEnum) {
		ArrayList<WinRangeStatsBean> totalStats = new ArrayList<WinRangeStatsBean>(); 
		for (TimeTypeEnum timeTypeEnum : timeTypeDao.findAllTimeTypeEnum()) {
			ArrayList<WinRangeStatsBean> homeStats = findByTeamNameAndChampAndTimeTypeAndPlayingField(teamName, champEnum, timeTypeEnum, "A");
			ArrayList<WinRangeStatsBean> awayStats = findByTeamNameAndChampAndTimeTypeAndPlayingField(teamName, champEnum, timeTypeEnum, "H");
			for (WinRangeStatsBean h : homeStats) {
				for (WinRangeStatsBean a : awayStats) {
					if (h.getRange().equals(a.getRange())) {
						WinRangeStatsBean t = new WinRangeStatsBean();

						t.setEdgeDown(h.getEdgeDown());
						t.setEdgeUp(h.getEdgeUp());
						t.setRange(h.getRange());
						t.setTeamName(h.getTeamName());
						t.setTotal(h.getTotal() + a.getTotal());
						
						t.setAwayHits(h.getAwayHits() + a.getAwayHits());
						t.setHomeHits(h.getHomeHits() + a.getHomeHits());
						
						t.setAwayMisses(h.getAwayMisses() + a.getAwayMisses());
						t.setHomeMisses(h.getHomeMisses() + a.getHomeMisses());
						
						t.setDrawHits(h.getDrawHits() + a.getDrawHits());
						t.setDrawMisses(h.getDrawMisses() + a.getDrawMisses());
						
						double total = h.getTotal().doubleValue() + a.getTotal().doubleValue();
						if (total!=0) {
							t.setWinPerc((h.getHomeHits() + a.getAwayHits()) / total);
							t.setDrawPerc((h.getDrawHits() + a.getDrawHits()) / total);
							t.setLosePerc((h.getAwayHits() + a.getHomeHits()) / total);
						}
						totalStats.add(t);
						
					}
				}
			}
			saveWinRangeStats(totalStats, teamName, champEnum, timeTypeEnum, "T");
		}
	}

	
	
	
	
	

}
