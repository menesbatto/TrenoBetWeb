package app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.dao.tabelle.MatchoRepo;
import app.dao.tabelle.entities.Matcho;
import app.logic.ResultParserNew;
import app.logic.UtilityModel;
import app.logic._1_matchResultParser.ResultParserNewModel;
import app.logic._1_matchResultParser.modelNew._1X2OddsBean;

@Controller    					// This means that this class is a Controller
@RequestMapping(path="/api2") 	// This means URL's start with /demo (after Application path)
public class FacadeController {
	@Autowired
	private ResultParserNewModel eventsOddsDownloader;

	@Autowired
	private UtilityModel utilityModel;
	
    @RequestMapping(value = "/eventsOddsDownloader", method = RequestMethod.GET)
    public @ResponseBody _1X2OddsBean eventsOddsDownloader () {
		
    	_1X2OddsBean betBean = eventsOddsDownloader.execute();
//    	BetBean betBean = null;
		return betBean;
	}

    @RequestMapping(value = "/initTipologiche", method = RequestMethod.GET)
    public @ResponseBody void initTipologiche () {
    	
    	utilityModel.execute();
    	initChampTable();
    }
    
    @RequestMapping(value = "/initChampTable", method = RequestMethod.GET)
    public @ResponseBody void initChampTable () {
    	
    	utilityModel.initChampsTable();
    }
    
    
    
    @Autowired
	private MatchoRepo matchRepo;
    @RequestMapping(value = "/getAllMatchResults", method = RequestMethod.GET)
    public @ResponseBody List<Matcho> getAllMatchResults() {
    	
    	List<Matcho> findAll = matchRepo.findAll();
		return findAll;
    }
    
    @Autowired
    private ResultParserNew resultParserNew;
    @RequestMapping(value = "/avviaVecchio1", method = RequestMethod.GET)
    public void avviaVecchio1() {
//    	initTipologiche();
    	resultParserNew.execute();
    }
    
    
    
    

//	@GetMapping(path="/all")
//	public @ResponseBody Iterable<User> getAllUsers() {
//		// This returns a JSON or XML with the users
//		return userRepository.findAll();
//	}
}