package learningresourcefinder.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Favorite extends BaseEntity {

	@Id   
	@GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Resource resource;

	@ManyToOne
	private User user;
	
	@Override
	public Long getId() {
		return id;
	}
	
	public User getUser() {
		return user;
	}
	
	public Resource getResource() {
		return resource;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
