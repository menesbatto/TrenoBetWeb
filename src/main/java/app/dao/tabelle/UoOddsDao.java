package app.dao.tabelle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.tabelle.entities.EhOdds;
import app.dao.tabelle.entities.UoOdds;
import app.dao.tabelle.entities._1X2Odds;
import app.dao.tipologiche.BetHouseDao;
import app.dao.tipologiche.HomeVariationTypeDao;
import app.dao.tipologiche.TimeTypeDao;
import app.dao.tipologiche.UoThresholdTypeDao;
import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.HomeVariationType;
import app.dao.tipologiche.entities.TimeType;
import app.dao.tipologiche.entities.UoThresholdType;
import app.logic._1_matchesDownlaoder.modelNew.EhOddsBean;
import app.logic._1_matchesDownlaoder.modelNew.UoOddsBean;
import app.logic._1_matchesDownlaoder.modelNew._1X2OddsBean;
import ma.glasnost.orika.MapperFacade;

@Service
public class UoOddsDao {


	@Autowired
	private UoOddsRepo betRepo;

	@Autowired
	private BetHouseDao betHouseDao;

	@Autowired
	private TimeTypeDao timeTypeDao;

	@Autowired
	private UoThresholdTypeDao uoThresholdTTypeDao;

	@Autowired
	private MapperFacade mapper;

	
	public UoOddsBean save(UoOddsBean betBean) {
		UoOdds betEnt = new UoOdds();
		BetHouse betHouseEnt = betHouseDao.findByValue(betBean.getBetHouseString());
		betEnt.setBetHouse(betHouseEnt);

		TimeType timeTypeEnt =  timeTypeDao.findByValue(betBean.getTimeTypeString());
		betEnt.setTimeType(timeTypeEnt);

		UoThresholdType UoThresholdTypeEnt =  uoThresholdTTypeDao.findByValue(betBean.getTimeTypeString());
		betEnt.setThresholdType(UoThresholdTypeEnt);
		
		
		mapper.map(betBean, betEnt);
		
		betEnt = betRepo.save(betEnt);
		
		mapper.map(betEnt, betBean);

		return betBean;
	}

}
