package app.dao;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app._0_eventsOddsDownloader.model.BetBean;
import app.dao.entities.Bet;
import app.dao.entities.BetHouse;
import ma.glasnost.orika.MapperFacade;

@Service
public class BetDao {


	@Autowired
	private BetRepo betRepo;

	@Autowired
	private BetHouseDao betHouseDao;

	@Autowired
	private MapperFacade mapper;

	
	public BetBean save(BetBean betBean) {
		Bet betEnt = new Bet();
		BetHouse betHouseEnt = betHouseDao.findByValue(betBean.getBetHouseString());
		betEnt.setBetHouse(betHouseEnt);
		mapper.map(betBean, betEnt);
		
		betEnt = betRepo.save(betEnt);
		
		mapper.map(betEnt, betBean);

		return betBean;
	}

}
