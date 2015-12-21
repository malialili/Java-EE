package fr.universite.bordeaux.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Annonce")
public class Annonce implements Serializable{
	private static final long serialVersionUID = 9129298985708507881L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String title;
	
	@Size(max=200)
	private String description;
	
	@Size(max=200)
	private String emplacement;
	
	@Column(nullable = false)
	private double prix;
	
	@Column(length = 4, nullable = false)
	private Integer surface;

	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@OneToMany(mappedBy="annonce")
	List<Picture> pictures;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
	User user;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public Integer getSurface() {
		return surface;
	}
	public void setSurface(Integer surface) {
		this.surface = surface;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
