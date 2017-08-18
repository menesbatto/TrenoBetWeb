package app;
 
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
 
import org.springframework.stereotype.Component;

import app._0_eventsOddsDownloader.model.BetBean;
import app.dao.entities.Bet;
 
@Component
public class MyCustomMapper extends ConfigurableMapper {
 
//    protected void configure(MapperFactory factory) {
//        factory.classMap(Bet.class, BetBean.class)
//             .field("_1", "_2")
//             .byDefault()
//            .register();
//    }
} 