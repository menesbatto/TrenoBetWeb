package app.dao.tabelle;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.Team;
import app.dao.tabelle.entities.WinRangeStats;
import app.dao.tipologiche.entities.BetHouse;

@RepositoryRestResource
public interface WinRangeStatsRepo extends PagingAndSortingRepository<WinRangeStats, Long> {

//	List<Team> findByName(String name);
//
//	List<BetHouse> findByValue(@Param("value") String string);

	List<WinRangeStats> findByTeam(Team team);
	
	
//	List<Person> findByLastName(@Param("name") String name);
//	
//	List<Person> findDistinctPeopleByFirstNameOrLastNameIgnoreCase(String lastname, String firstname);
	
//	List<Person> findDistinctPeopleByLastnameOrFi4rstname(String lastname, String firstname);

}
