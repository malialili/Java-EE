package fr.universite.bordeaux.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="SearchCriterion")
public class SearchCriterion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne(cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	@JoinColumn(name="idUser", referencedColumnName="id")
	private User user;
	@Column(length=50)
	private String type;
	@Column(length=50)
	private String ville;
	private double prix_min;
	private double prix_max;
	private int surface_min;
	private int surface_max;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public double getPrix_min() {
		return prix_min;
	}
	public void setPrix_min(double prix_min) {
		this.prix_min = prix_min;
	}
	public double getPrix_max() {
		return prix_max;
	}
	public void setPrix_max(double prix_max) {
		this.prix_max = prix_max;
	}
	public int getSurface_min() {
		return surface_min;
	}
	public void setSurface_min(int surface_min) {
		this.surface_min = surface_min;
	}
	public int getSurface_max() {
		return surface_max;
	}
	public void setSurface_max(int surface_max) {
		this.surface_max = surface_max;
	}
}