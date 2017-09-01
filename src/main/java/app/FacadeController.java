package app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.dao.tabelle.MatchoRepo;
import app.dao.tabelle.entities.Matcho;
import app.logic.UtilityModel;
import app.logic._1_matchesDownlaoder.MatchesDownloader;
import app.logic._1_matchesDownlaoder.NextMatchesDownloader;
import app.logic._1_matchesDownlaoder.PastMatchesDownlaoder;
import app.logic._1_matchesDownlaoder.ResultParserOLD;
import app.logic._1_matchesDownlaoder.modelNew._1X2OddsBean;
import app.logic._2_matchResultAnalyzer.ResultAnalyzer;

@Controller    					// This means that this class is a Controller
@RequestMapping(path="/api2") 	// This means URL's start with /demo (after Application path)
public class FacadeController {
	@Autowired
	private ResultParserOLD eventsOddsDownloaderOld;

	@Autowired
	private UtilityModel utilityModel;
	
    @RequestMapping(value = "/eventsOddsDownloader", method = RequestMethod.GET)
    public @ResponseBody _1X2OddsBean eventsOddsDownloader () {
		
    	_1X2OddsBean betBean = eventsOddsDownloaderOld.execute();
//    	BetBean betBean = null;
		return betBean;
	}

    @RequestMapping(value = "/initTipologiche", method = RequestMethod.GET)
    public @ResponseBody void initTipologiche () {
    	
    	utilityModel.execute();
    	initChampTable();
    }
    
    
    @RequestMapping(value = "/resetStats", method = RequestMethod.GET)
    public @ResponseBody void resetStats () {
    	utilityModel.deleteAllWinRangeStats();
    	utilityModel.deleteAllGoalsStats();
    }
    
    @RequestMapping(value = "/resetMatches", method = RequestMethod.GET)
    public @ResponseBody void resetMatches () {
    	utilityModel.deleteAllMatches();
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
    private NextMatchesDownloader nextMatchesDownloader;
    @RequestMapping(value = "/avviaVecchio0", method = RequestMethod.GET)
    public void avviaVecchio0() {
    	nextMatchesDownloader.execute();
    }

    @Autowired
    private PastMatchesDownlaoder pastMatchesDownloader;
    @RequestMapping(value = "/avviaVecchio1", method = RequestMethod.GET)
    public void avviaVecchio1() {
    	pastMatchesDownloader.execute();
    }

    @Autowired
    private ResultAnalyzer resultAnalyzer;
    @RequestMapping(value = "/avviaVecchio3", method = RequestMethod.GET)
    public void avviaVecchio3() {
    	resultAnalyzer.execute();
    }
    
    
    
    

//	@GetMapping(path="/all")
//	public @ResponseBody Iterable<User> getAllUsers() {
//		// This returns a JSON or XML with the users
//		return userRepository.findAll();
//	}
}