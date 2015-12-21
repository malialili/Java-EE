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
import fr.universite.bordeaux.entities.Picture;

@Stateless
public class PictureRepository {
    private static final String JPQL_SELECT_PAR_ANNONCE = "SELECT a FROM Annonce a WHERE a.annonce=:annonce";
    private static final String PARAM_ANNONCE = "annonce";
    @PersistenceContext(unitName = "aldaPersistenceUnit")
    private EntityManager entityManager;

    public void addPicture(Picture picture){
        entityManager.persist(picture);
    }

    public List<Picture> findPicturesByAnnonce(Annonce annonce) {
        Query requete = entityManager.createQuery(JPQL_SELECT_PAR_ANNONCE );
        requete.setParameter(PARAM_ANNONCE, annonce);
        @SuppressWarnings("unchecked")
        List<Picture> pictures = (List<Picture>)requete.getResultList();
        return pictures;
    }
    @SuppressWarnings("unchecked")
 	public List<Picture> getAllThePictures(){
 		return entityManager.createNativeQuery("select * from Picture", Picture.class)
                 .getResultList();
 	}
     @GET
     @Path("{id}")
     public Picture getPicture( @PathParam("id") Long id) {
         return entityManager.find(Picture.class, id);
     }
}
