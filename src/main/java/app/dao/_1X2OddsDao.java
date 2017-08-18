package app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app._0_eventsOddsDownloader.model._1X2OddsBean;
import app.dao.entities._1X2Odds;
import app.dao.entities.BetHouse;
import ma.glasnost.orika.MapperFacade;

@Service
public class _1X2OddsDao {


	@Autowired
	private _1X2OddsRepo betRepo;

	@Autowired
	private BetHouseDao betHouseDao;

	@Autowired
	private MapperFacade mapper;

	
	public _1X2OddsBean save(_1X2OddsBean betBean) {
		_1X2Odds betEnt = new _1X2Odds();
		BetHouse betHouseEnt = betHouseDao.findByValue(betBean.getBetHouseString());
		betEnt.setBetHouse(betHouseEnt);
		mapper.map(betBean, betEnt);
		
		betEnt = betRepo.save(betEnt);
		
		mapper.map(betEnt, betBean);

		return betBean;
	}

}
