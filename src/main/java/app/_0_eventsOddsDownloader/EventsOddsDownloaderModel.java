package app._0_eventsOddsDownloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app._0_eventsOddsDownloader.model.BetBean;
import app.dao.BetRepo;
import app.dao.entities.Bet;
import ma.glasnost.orika.MapperFacade;

@Service
public class EventsOddsDownloaderModel {

	@Autowired
	private MapperFacade mapper;

	@Autowired
	private BetRepo betRepo;
	
	
	
	
	public BetBean execute() {
		BetBean betBean = new BetBean("1.1","2.1","3.1");
		Bet bet = new Bet();
		mapper.map(betBean, bet);
		bet = betRepo.save(bet);
		
		mapper.map(bet, betBean);
		
		return betBean;
		
		
	}
	
}
