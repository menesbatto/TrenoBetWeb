package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app._0_eventsOddsDownloader.EventsOddsDownloaderModel;
import app._0_eventsOddsDownloader.UtilityModel;
import app._0_eventsOddsDownloader.model._1X2OddsBean;

@Controller    					// This means that this class is a Controller
@RequestMapping(path="/api2") 	// This means URL's start with /demo (after Application path)
public class FacadeController {
	@Autowired
	private EventsOddsDownloaderModel eventsOddsDownloader;

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
    }
    
    

//	@GetMapping(path="/all")
//	public @ResponseBody Iterable<User> getAllUsers() {
//		// This returns a JSON or XML with the users
//		return userRepository.findAll();
//	}
}