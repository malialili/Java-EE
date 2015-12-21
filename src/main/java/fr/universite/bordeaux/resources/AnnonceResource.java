package fr.universite.bordeaux.resources;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.universite.bordeaux.entities.Annonce;
import fr.universite.bordeaux.entities.User;
import fr.universite.bordeaux.repositories.AnnonceRepository;
import fr.universite.bordeaux.repositories.UserRepository;

@Path("/annonces")
public class AnnonceResource {
    @EJB
    AnnonceRepository annonceRepository;
    @EJB
    UserRepository userRepository;
    
    @GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Annonce> getAllAnnonces(){
		return annonceRepository.getAllTheAnnonces();
	}
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Annonce> getAnnoncesByUser(){
        User user = userRepository.findUserByEmail("malia.mias@yahoo.fr");
        return annonceRepository.findAnnoncesByUser(user);
    }
    
    @POST
    @Path("/addAnnonce")
    @Consumes("application/json")
    public void  addUser(Annonce annonce){
        User user = userRepository.findUserByEmail("malia.lili1@gmail.com");
        annonce.setCreatedDate(new Date());
        annonce.setUser(user);
        annonceRepository.addAnnonce(annonce);
    }
}
