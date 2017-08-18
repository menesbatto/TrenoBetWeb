package app.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.entities.BetHouse;

@Service
public class BetHouseDao {

	@Autowired
	private BetHouseRepo betHouseRepo;
	
	private HashMap<String, BetHouse> betHousesMap;
	
	public BetHouse findByValue(String betHouseString) {
		if (betHousesMap == null)
			initBetHousesMap();
			
		BetHouse betHouse = betHousesMap.get(betHouseString);
		
		return betHouse;
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
