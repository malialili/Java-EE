package fr.universite.bordeaux.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;

import fr.universite.bordeaux.repositories.SearchCriterionRepository;
import fr.universite.bordeaux.entities.SearchCriterion;

@Path("/criteres")
public class SearchCriterionResource {
	
	@EJB
	private SearchCriterionRepository searchCriterionRepository;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<SearchCriterion> getCriteres(){
		return searchCriterionRepository.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public SearchCriterion getCritere(@PathParam("id") long idCritere){
		return searchCriterionRepository.getId(idCritere);
	}	
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public void addCritere(SearchCriterion critere){
		searchCriterionRepository.saveOrUpdate(critere);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	public void updateCritere(SearchCriterion critere){
		searchCriterionRepository.saveOrUpdate(critere);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteCritere(@PathParam("id") long idCritere){
		searchCriterionRepository.delete(idCritere);
	}
}