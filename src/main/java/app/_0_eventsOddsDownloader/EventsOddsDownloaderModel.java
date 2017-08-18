package app._0_eventsOddsDownloader;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app._0_eventsOddsDownloader.model.BetBean;
import app.dao.BetDao;
import app.dao.BetHouseDao;

@Service
public class EventsOddsDownloaderModel {



	@Autowired
	private BetDao betDao;


	@Autowired
	private BetHouseDao betHouseDao;
	
	
	
	
	public BetBean execute() {
		
		BetBean betBean = new BetBean("1.1","2.1","3.1");
		betBean.setBetHouseString("Ciao");
		betBean = betDao.save(betBean);
		
		return betBean;
		
		
	}
	
}
