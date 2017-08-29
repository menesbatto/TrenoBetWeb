package app.dao.tabelle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.Team;
import app.dao.tabelle.entities.WinRangeStats;
import app.dao.tipologiche.OddsRangeDao;
import app.dao.tipologiche.OddsRangeRepo;
import app.dao.tipologiche.entities.OddsRange;
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
	private MapperFacade mapper;
	
	public ArrayList<WinRangeStatsBean> findByTeamNameAndChamp(String teamName, ChampEnum champEnum) {
		Champ champ = champDao.findByChampEnum(champEnum);
		Team team = teamDao.findByNameAndChamp(teamName, champ);
		List<WinRangeStats> listEnt = winRangeStatsRepo.findByTeam(team);
			
		ArrayList<WinRangeStatsBean> listBean= new ArrayList<WinRangeStatsBean>();
		for (WinRangeStats ent : listEnt) {
			WinRangeStatsBean bean = new WinRangeStatsBean();
			mapper.map(bean, ent);
			bean.setEdgeDown(ent.getRange().getValueDown());
			bean.setEdgeUp(ent.getRange().getValueUp());
			bean.setTeamName(ent.getTeam().getName());
			listBean.add(bean);
		}

		return listBean;
	}
	
	
	
	public List<WinRangeStats> findByTeam(Team team) {
		List<WinRangeStats> list = winRangeStatsRepo.findByTeam(team);
		if (list.isEmpty())
			list = saveWinRangeStats(team);
		
		return list;
		
	}

	public List<WinRangeStats> saveWinRangeStats(Team team) {
		List<WinRangeStats> winRangeStatsList = new ArrayList<WinRangeStats>();
		List<OddsRange> oddsRangeList = oddsRangeDao.findAll();
		for(OddsRange range: oddsRangeList) {
			WinRangeStats winRange = new WinRangeStats(range, team);
			winRangeStatsList.add(winRange);			
		}
		winRangeStatsRepo.save(winRangeStatsList);
		return winRangeStatsList;
	}

	
	public List<WinRangeStats> saveWinRangeStats(List<WinRangeStatsBean> listBean, ChampEnum champEnum) {
		Champ champ = champDao.findByChampEnum(champEnum);
		List<WinRangeStats> winRangeStatsList = null;
		for (WinRangeStatsBean bean : listBean) {
			WinRangeStats ent = new WinRangeStats();
			OddsRange rangeEnt = oddsRangeDao.findByValue(bean.getEdgeUp());
			ent.setRange(rangeEnt);

			Team teamEnt = teamDao.findByNameAndChamp(bean.getTeamName(), champ);
			ent.setTeam(teamEnt);
		}
		
		winRangeStatsRepo.save(winRangeStatsList);
		return winRangeStatsList;
	}

	
	
	
	
	

}
