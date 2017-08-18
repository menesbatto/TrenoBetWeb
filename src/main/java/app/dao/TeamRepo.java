package app.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.entities.BetHouse;
import app.dao.entities.Champ;
import app.dao.entities.Team;

@RepositoryRestResource
public interface TeamRepo extends PagingAndSortingRepository<Team, Long> {

	List<Team> findByName(String name);
//
//	List<BetHouse> findByValue(@Param("value") String string);
	
	
//	List<Person> findByLastName(@Param("name") String name);
//	
//	List<Person> findDistinctPeopleByFirstNameOrLastNameIgnoreCase(String lastname, String firstname);
	
//	List<Person> findDistinctPeopleByLastnameOrFi4rstname(String lastname, String firstname);

}
