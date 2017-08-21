package app.dao.tipologiche;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.HomeVariationType;
import app.dao.tipologiche.entities.TimeType;
import app.dao.tipologiche.entities.UoThresholdType;

@Service
public class UoThresholdTypeDao {

	@Autowired
	private UoThresholdTypeRepo uoThresholdTypeRepo;
	
	private HashMap<String, UoThresholdType> cacheMap;
	
	public UoThresholdType findByValue(String uoThresholdString) {
		if (cacheMap == null || cacheMap.isEmpty())
			initCacheMap();
			
		UoThresholdType uoThresholdType = cacheMap.get(uoThresholdString);
		
		return uoThresholdType;
	}

	public void initTable() {
		UoThresholdType _0_5 = new UoThresholdType("_0_5");
		uoThresholdTypeRepo.save(_0_5);
		UoThresholdType _1_5 = new UoThresholdType("_1_5");
		uoThresholdTypeRepo.save(_1_5);
		UoThresholdType _2_5 = new UoThresholdType("_2_5");
		uoThresholdTypeRepo.save(_2_5);
		UoThresholdType _3_5 = new UoThresholdType("_3_5");
		uoThresholdTypeRepo.save(_3_5);
		UoThresholdType _4_5 = new UoThresholdType("_4_5");
		uoThresholdTypeRepo.save(_4_5);
		UoThresholdType _5_5 = new UoThresholdType("_5_5");
		uoThresholdTypeRepo.save(_5_5);
		UoThresholdType _6_5 = new UoThresholdType("_6_5");
		uoThresholdTypeRepo.save(_6_5);
		UoThresholdType _7_5 = new UoThresholdType("_7_5");
		uoThresholdTypeRepo.save(_7_5);
		UoThresholdType _8_5 = new UoThresholdType("_8_5");
		uoThresholdTypeRepo.save(_8_5);
		UoThresholdType _9_5 = new UoThresholdType("_9_5");
		uoThresholdTypeRepo.save(_9_5);
		UoThresholdType _10_5 = new UoThresholdType("_10_5");
		uoThresholdTypeRepo.save(_10_5);
	}
	
	private void initCacheMap() {
		cacheMap = new HashMap<String, UoThresholdType>();
		Iterable<UoThresholdType> findAll = uoThresholdTypeRepo.findAll();
		for (Iterator<UoThresholdType> iter = findAll.iterator(); iter.hasNext(); ) {
			UoThresholdType element = iter.next();
			cacheMap.put(element.getValue(), element);
		}	
	}
	
}
