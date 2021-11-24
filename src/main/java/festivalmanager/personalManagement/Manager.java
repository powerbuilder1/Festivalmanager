package festivalmanager.personalManagement;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public  class Manager  {
	private @Id @GeneratedValue long id;
	private String name;


	public Manager() {
		super();
	}

	public Manager(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
