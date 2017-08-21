package app;
 
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
 
import org.springframework.stereotype.Component;

import app.dao.tabelle.entities._1X2Odds;
import app.logic.app._0_eventsOddsDownloader.model._1X2OddsBean;
 
@Component
public class MyCustomMapper extends ConfigurableMapper {
 
//    protected void configure(MapperFactory factory) {
//        factory.classMap(Bet.class, BetBean.class)
//             .field("_1", "_2")
//             .byDefault()
//            .register();
//    }
} 