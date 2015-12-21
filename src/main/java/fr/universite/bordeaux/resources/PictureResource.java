package fr.universite.bordeaux.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.universite.bordeaux.entities.Annonce;
import fr.universite.bordeaux.entities.Picture;
import fr.universite.bordeaux.entities.User;
import fr.universite.bordeaux.repositories.AnnonceRepository;
import fr.universite.bordeaux.repositories.PictureRepository;
import fr.universite.bordeaux.repositories.UserRepository;

@Path("/pictures")
public class PictureResource {
    @EJB
    AnnonceRepository annonceRepository;
    @EJB
    UserRepository userRepository;
    @EJB
    PictureRepository pictureRepository;
    
    @GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Picture> getAllPictures(){
		return pictureRepository.getAllThePictures();
	}
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Picture> getPicturesByAnnonce(){
    	User user= userRepository.findUserByEmail("malia.mias@yahoo.fr");
        Annonce annonce = (Annonce) annonceRepository.findAnnoncesByUser(user);
        return pictureRepository.findPicturesByAnnonce(annonce);
    }
    
    @POST
    @Path("/addPicture")
    @Consumes("application/json")
    public void  addPicture(Picture picture){
         pictureRepository.addPicture(picture);
    }
}
