package app.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.entities.BetHouse;
import app.dao.entities.TimeType;

@Service
public class BetHouseDao {

	@Autowired
	private BetHouseRepo betHouseRepo;
	
	private HashMap<String, BetHouse> betHousesMap;
	
	public BetHouse findByValue(String betHouseString) {
		if (betHousesMap == null || betHousesMap.isEmpty())
			initBetHousesMap();
			
		BetHouse betHouse = betHousesMap.get(betHouseString);
		
		return betHouse;
	}
	
	public void initTable() {
		//bet365, Betclic,  bwin, PaddyPower, Tipico, Unibet, WilliamHill

		BetHouse bet365 = new BetHouse("bet365");
		betHouseRepo.save(bet365);
		BetHouse Betclic = new BetHouse("Betclic");
		betHouseRepo.save(Betclic);
		BetHouse bwin = new BetHouse("bwin");
		betHouseRepo.save(bwin);
		BetHouse PaddyPower = new BetHouse("PaddyPower");
		betHouseRepo.save(PaddyPower);
		BetHouse Tipico = new BetHouse("Tipico");
		betHouseRepo.save(Tipico);
		BetHouse Unibet = new BetHouse("Unibet");
		betHouseRepo.save(Unibet);
		BetHouse WilliamHill = new BetHouse("WilliamHill");
		betHouseRepo.save(WilliamHill);


	}
	
	

	private void initBetHousesMap() {
		betHousesMap = new HashMap<String, BetHouse>();
		Iterable<BetHouse> findAll = betHouseRepo.findAll();
		for (Iterator<BetHouse> iter = findAll.iterator(); iter.hasNext(); ) {
			BetHouse element = iter.next();
			betHousesMap.put(element.getValue(), element);
		}	
	}
	
//	private HashMap<>
//	
//	public List<BetHouse> getAllBetHouses() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
