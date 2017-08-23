package app.dao.tabelle;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.dao.tabelle.entities.ChampImpPos;
import app.dao.tabelle.entities.ChampUrls;

@RepositoryRestResource
public interface ChampImpPosRepo extends PagingAndSortingRepository<ChampImpPos, Long> {

}
