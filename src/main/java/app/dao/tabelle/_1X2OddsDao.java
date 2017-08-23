package app.dao.tabelle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities._1X2Odds;
import app.dao.tipologiche.BetHouseDao;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.TimeType;
import app.logic._1_matchResultParser.modelNew._1X2OddsBean;
import ma.glasnost.orika.MapperFacade;

@Service
public class _1X2OddsDao {


	@Autowired
	private _1X2OddsRepo betRepo;

	@Autowired
	private BetHouseDao betHouseDao;

	@Autowired
	private TimeTypeDao timeTypeDao;

	@Autowired
	private MapperFacade mapper;

	
	public _1X2OddsBean save(_1X2OddsBean betBean) {
		_1X2Odds betEnt = new _1X2Odds();
		BetHouse betHouseEnt = betHouseDao.findByValue(betBean.getBetHouseString());
		betEnt.setBetHouse(betHouseEnt);
		
		TimeType timeTypeEnt =  timeTypeDao.findByValue(betBean.getTimeTypeString());
		betEnt.setTimeType(timeTypeEnt);
		
		mapper.map(betBean, betEnt);
		
		betEnt = betRepo.save(betEnt);
		
		mapper.map(betEnt, betBean);

		return betBean;
	}

}
