package fr.universite.bordeaux.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.*;
//import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import fr.universite.bordeaux.entities.User;


@Stateless
@ApplicationPath("/resources")
@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRepository {
	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM User u WHERE u.email=:email";
	private static final String PARAM_EMAIL = "email";
	@PersistenceContext(unitName = "aldaPersistenceUnit")
	private EntityManager entityManager;

	public void addUser(User user){
		entityManager.persist(user);
	}
	
	
	@SuppressWarnings("unused")
	private Integer countUsers() {
        Query query = entityManager.createQuery("SELECT COUNT(p.id) FROM User p");
        return ((Long) query.getSingleResult()).intValue();
    }
	
	public User findUserByEmail(String email) {
		Query requete = entityManager.createQuery(JPQL_SELECT_PAR_EMAIL);
		requete.setParameter(PARAM_EMAIL, email);
		User user = (User) requete.getSingleResult();
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllTheUsers(){
		return entityManager.createNativeQuery("select * from User", User.class)
                .getResultList();
	}
	
    @GET
    @Path("{id}")
    public User getUser( @PathParam("id") Long id) {
        return entityManager.find(User.class, id);
    }
 
    @DELETE
    @Path("{id}")
    public void deleteUser(@PathParam("id") Long id) {
        entityManager.remove(getUser(id));
    }
	
	public void updateUser(User user){
		
		entityManager.merge(user);
	}
	
	public void deleteUser(String email){
		Query requete = entityManager.createNativeQuery("select * from User where email='"+email+"'", User.class);
		User user = (User) requete.getSingleResult();
		entityManager.remove(user);
	}
}