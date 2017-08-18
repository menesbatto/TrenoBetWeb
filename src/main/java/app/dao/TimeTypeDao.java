package app.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.entities.BetHouse;
import app.dao.entities.TimeType;

@Service
public class TimeTypeDao {

	@Autowired
	private TimeTypeRepo timeTypeRepo;
	
	private HashMap<String, TimeType> timeTypeMap;
	
	public TimeType findByValue(String betHouseString) {
		if (timeTypeMap == null)
			initTimeTypeMap();
			
		TimeType tymeType = timeTypeMap.get(betHouseString);
		
		return tymeType;
	}

	public void initTable() {
		TimeType _final = new TimeType("final");
		timeTypeRepo.save(_final);
		TimeType _1 = new TimeType("_1");
		timeTypeRepo.save(_1);
		TimeType _2 = new TimeType("_2");
		timeTypeRepo.save(_2);
	}
	
	private void initTimeTypeMap() {
		timeTypeMap = new HashMap<String, TimeType>();
		Iterable<TimeType> findAll = timeTypeRepo.findAll();
		for (Iterator<TimeType> iter = findAll.iterator(); iter.hasNext(); ) {
			TimeType element = iter.next();
			timeTypeMap.put(element.getValue(), element);
		}	
	}
	
}
