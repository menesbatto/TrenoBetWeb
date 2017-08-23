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
		UoThresholdType _0_5 = new UoThresholdType(0, "_0_5");
		uoThresholdTypeRepo.save(_0_5);
		UoThresholdType _1_5 = new UoThresholdType(1, "_1_5");
		uoThresholdTypeRepo.save(_1_5);
		UoThresholdType _2_5 = new UoThresholdType(2, "_2_5");
		uoThresholdTypeRepo.save(_2_5);
		UoThresholdType _3_5 = new UoThresholdType(3, "_3_5");
		uoThresholdTypeRepo.save(_3_5);
		UoThresholdType _4_5 = new UoThresholdType(4, "_4_5");
		uoThresholdTypeRepo.save(_4_5);
		UoThresholdType _5_5 = new UoThresholdType(5, "_5_5");
		uoThresholdTypeRepo.save(_5_5);
		UoThresholdType _6_5 = new UoThresholdType(6, "_6_5");
		uoThresholdTypeRepo.save(_6_5);
		UoThresholdType _7_5 = new UoThresholdType(7, "_7_5");
		uoThresholdTypeRepo.save(_7_5);
		UoThresholdType _8_5 = new UoThresholdType(8, "_8_5");
		uoThresholdTypeRepo.save(_8_5);
		UoThresholdType _9_5 = new UoThresholdType(9, "_9_5");
		uoThresholdTypeRepo.save(_9_5);
		UoThresholdType _10_5 = new UoThresholdType(10, "_10_5");
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
