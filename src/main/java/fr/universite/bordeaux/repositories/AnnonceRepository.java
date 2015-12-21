package fr.universite.bordeaux.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import fr.universite.bordeaux.entities.Annonce;
import fr.universite.bordeaux.entities.User;

@Stateless
public class AnnonceRepository {
    private static final String JPQL_SELECT_PAR_EMAIL = "SELECT a FROM Annonce a WHERE a.user=:user";
    private static final String PARAM_USER = "user";
    @PersistenceContext(unitName = "aldaPersistenceUnit")
    private EntityManager entityManager;

    public void addAnnonce(Annonce annonce){
        entityManager.persist(annonce);
    }

    public List<Annonce> findAnnoncesByUser(User user) {
        Query requete = entityManager.createQuery(JPQL_SELECT_PAR_EMAIL);
        requete.setParameter(PARAM_USER, user);
        @SuppressWarnings("unchecked")
        List<Annonce> annonces = (List<Annonce>)requete.getResultList();
        return annonces;
    }
    @SuppressWarnings("unchecked")
 	public List<Annonce> getAllTheAnnonces(){
 		return entityManager.createNativeQuery("select * from Annonce", Annonce.class)
                 .getResultList();
 	}
     @GET
     @Path("{id}")
     public Annonce getAnnonce( @PathParam("id") Long id) {
         return entityManager.find(Annonce.class, id);
     }
}
