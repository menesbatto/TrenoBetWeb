package app.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.entities.Champ;
import app.dao.entities.Matcho;
import app.dao.entities.TimeType;
import app.dao.entities._1X2Odds;

@RepositoryRestResource
public interface MatchoRepo extends PagingAndSortingRepository<Matcho, Long> {

//	List<Match> findByNameAndStartYearAndNation(String name, int startYear, String nation);
//	
//	List<Person> findDistinctPeopleByFirstNameOrLastNameIgnoreCase(String lastname, String firstname);
	
//	List<Person> findDistinctPeopleByLastnameOrFi4rstname(String lastname, String firstname);

}
