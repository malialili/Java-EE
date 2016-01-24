package fr.universite.bordeaux.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.universite.bordeaux.entities.SearchCriterion;

@Stateless
public class SearchCriterionRepository {

	private static final String JPQL_SELECT = "SELECT c FROM SearchCriterion c";
	private static final String JPQL_SELECT_PAR_PERSONNE = "SELECT c FROM SearchCriterion c WHERE c.user.id=:idUser";
	private static final String PARAM_PERSONNE = "idUser";
	@PersistenceContext(unitName = "aldaPersistenceUnit")
	private EntityManager entityManager;
	
	public void saveOrUpdate(SearchCriterion critere){
		entityManager.merge(critere);
	}
	
	@SuppressWarnings("unchecked")
	public List<SearchCriterion> getAll(){
        Query query=(Query) entityManager.createQuery(JPQL_SELECT);
        List<SearchCriterion> criteres=(List<SearchCriterion>)((javax.persistence.Query) query).getResultList();
        return criteres;		
	}
	
	public SearchCriterion getByUser (long idUser){
		Query query=(Query) entityManager.createQuery(JPQL_SELECT_PAR_PERSONNE);
		((javax.persistence.Query) query).setParameter(PARAM_PERSONNE, idUser);
		SearchCriterion critere = (SearchCriterion)((javax.persistence.Query) query).getSingleResult();
		return critere;
	}
	
	public SearchCriterion getId(long id){
		return entityManager.find(SearchCriterion.class, id);
	}
	
	public void delete(long id){
		SearchCriterion critere = getId(id);
		entityManager.remove(critere);
	}
}