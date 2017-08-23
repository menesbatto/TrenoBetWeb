package app.dao.tabelle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.EhOdds;
import app.dao.tabelle.entities._1X2Odds;
import app.dao.tipologiche.BetHouseDao;
import app.dao.tipologiche.HomeVariationTypeDao;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.HomeVariationType;
import app.dao.tipologiche.entities.TimeType;
import app.logic._1_matchResultParser.modelNew.EhOddsBean;
import app.logic._1_matchResultParser.modelNew._1X2OddsBean;
import ma.glasnost.orika.MapperFacade;

@Service
public class EhOddsDao {


	@Autowired
	private EhOddsRepo betRepo;

	@Autowired
	private BetHouseDao betHouseDao;

	@Autowired
	private TimeTypeDao timeTypeDao;

	@Autowired
	private HomeVariationTypeDao homeVariationTypeDao;

	@Autowired
	private MapperFacade mapper;

	
	public EhOddsBean save(EhOddsBean betBean) {
		EhOdds betEnt = new EhOdds();
		BetHouse betHouseEnt = betHouseDao.findByValue(betBean.getBetHouseString());
		betEnt.setBetHouse(betHouseEnt);

		TimeType timeTypeEnt =  timeTypeDao.findByValue(betBean.getTimeTypeString());
		betEnt.setTimeType(timeTypeEnt);

		HomeVariationType homeVariationTypeEnt =  homeVariationTypeDao.findByValue(betBean.getTimeTypeString());
		betEnt.setHomeVariationType(homeVariationTypeEnt);
		
		
		mapper.map(betBean, betEnt);
		
		betEnt = betRepo.save(betEnt);
		
		mapper.map(betEnt, betBean);

		return betBean;
	}

}
