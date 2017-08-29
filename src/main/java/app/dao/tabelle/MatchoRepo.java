package app.dao.tabelle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.tabelle.entities.Champ;
import app.dao.tabelle.entities.Matcho;
import app.dao.tabelle.entities.Team;

@RepositoryRestResource
public interface MatchoRepo extends JpaRepository<Matcho, Long> {

	List<Matcho> findAll();

	Long countByChamp(Champ champ);

	List<Matcho> findByChamp(Champ champ);

	List<Matcho> findByChampAndHomeTeam(Champ champ, Team team);
	List<Matcho> findByChampAndAwayTeam(Champ champ, Team team);
//	List<Match> findByNameAndStartYearAndNation(String name, int startYear, String nation);
//	
//	List<Person> findDistinctPeopleByFirstNameOrLastNameIgnoreCase(String lastname, String firstname);

	
//	List<Person> findDistinctPeopleByLastnameOrFi4rstname(String lastname, String firstname);

}
