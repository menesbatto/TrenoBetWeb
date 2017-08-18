package app.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app._0_eventsOddsDownloader.model.TeamBean;
import app.dao.entities.BetHouse;
import app.dao.entities.Champ;
import app.dao.entities.Team;
import app.dao.entities.TimeType;

@Service
public class TeamDao {

	@Autowired
	private TeamRepo teamRepo;

	@Autowired
	private ChampDao champDao;
	
//	private HashMap<String, Team> teamsMap;
	
	public Team findByName(String name) {
		List<Team> list = teamRepo.findByName(name);
		Team first = list.get(0);
		return first;
		
	}
	
	public void initTable() {
		//bet365, Betclic,  bwin, PaddyPower, Tipico, Unibet, WilliamHill
		Champ serieA = champDao.findByNameAndStartYearAndNation("Serie A", 2017, "Italy");
		Team juventus = new Team("juventus", serieA);
		teamRepo.save(juventus);
		Team inter = new Team("inter", serieA);
		teamRepo.save(inter);
		Team roma = new Team("roma", serieA);
		teamRepo.save(roma);
		Team pescara = new Team("pescara", serieA);
		teamRepo.save(pescara);


	}
	
	

	private void initBetHousesMap() {
//		teamsMap = new HashMap<String, BetHouse>();
//		Iterable<BetHouse> findAll = teamRepo.findAll();
//		for (Iterator<BetHouse> iter = findAll.iterator(); iter.hasNext(); ) {
//			BetHouse element = iter.next();
//			teamsMap.put(element.getValue(), element);
//		}	
	}

	public Team findByName(TeamBean team) {
		String teamName = team.getName();
		List<Team> list = teamRepo.findByName(teamName);
		Team first = list.get(0);
		return first;
	}
}
