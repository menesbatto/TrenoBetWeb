package app.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.entities.BetHouse;

@RepositoryRestResource
public interface BetHouseRepo extends PagingAndSortingRepository<BetHouse, Long> {
	@Cacheable("betHouses")
	List<BetHouse> findAll();
	
	
//	List<Person> findByLastName(@Param("name") String name);
//	
//	List<Person> findDistinctPeopleByFirstNameOrLastNameIgnoreCase(String lastname, String firstname);
	
//	List<Person> findDistinctPeopleByLastnameOrFi4rstname(String lastname, String firstname);

}
