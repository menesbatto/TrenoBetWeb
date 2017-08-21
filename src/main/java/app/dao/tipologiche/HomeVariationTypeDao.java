package app.dao.tipologiche;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.HomeVariationType;
import app.dao.tipologiche.entities.TimeType;

@Service
public class HomeVariationTypeDao {

	@Autowired
	private HomeVariationTypeRepo homeVariationTypeRepo;
	
	private HashMap<String, HomeVariationType> cacheMap;
	
	public HomeVariationType findByValue(String homeVariationString) {
		if (cacheMap == null || cacheMap.isEmpty())
			initCacheMap();
			
		HomeVariationType homeVariationType = cacheMap.get(homeVariationString);
		
		return homeVariationType;
	}

	public void initTable() {
		HomeVariationType m9 = new HomeVariationType("m9");
		homeVariationTypeRepo.save(m9);
		HomeVariationType m8 = new HomeVariationType("m8");
		homeVariationTypeRepo.save(m8);
		HomeVariationType m7 = new HomeVariationType("m7");
		homeVariationTypeRepo.save(m7);
		HomeVariationType m6 = new HomeVariationType("m6");
		homeVariationTypeRepo.save(m6);
		HomeVariationType m5 = new HomeVariationType("m5");
		homeVariationTypeRepo.save(m5);
		HomeVariationType m4 = new HomeVariationType("m4");
		homeVariationTypeRepo.save(m4);
		HomeVariationType m3 = new HomeVariationType("m3");
		homeVariationTypeRepo.save(m3);
		HomeVariationType m2 = new HomeVariationType("m2");
		homeVariationTypeRepo.save(m2);
		HomeVariationType m1 = new HomeVariationType("m1");
		homeVariationTypeRepo.save(m1);

		HomeVariationType p9 = new HomeVariationType("p9");
		homeVariationTypeRepo.save(p9);
		HomeVariationType p8 = new HomeVariationType("p8");
		homeVariationTypeRepo.save(p8);
		HomeVariationType p7 = new HomeVariationType("p7");
		homeVariationTypeRepo.save(p7);
		HomeVariationType p6 = new HomeVariationType("p6");
		homeVariationTypeRepo.save(p6);
		HomeVariationType p5 = new HomeVariationType("p5");
		homeVariationTypeRepo.save(p5);
		HomeVariationType p4 = new HomeVariationType("p4");
		homeVariationTypeRepo.save(p4);
		HomeVariationType p3 = new HomeVariationType("p3");
		homeVariationTypeRepo.save(p3);
		HomeVariationType p2 = new HomeVariationType("p2");
		homeVariationTypeRepo.save(p2);
		HomeVariationType p1 = new HomeVariationType("p1");
		homeVariationTypeRepo.save(m1);
	}
	
	private void initCacheMap() {
		cacheMap = new HashMap<String, HomeVariationType>();
		Iterable<HomeVariationType> findAll = homeVariationTypeRepo.findAll();
		for (Iterator<HomeVariationType> iter = findAll.iterator(); iter.hasNext(); ) {
			HomeVariationType element = iter.next();
			cacheMap.put(element.getValue(), element);
		}	
	}
	
}
