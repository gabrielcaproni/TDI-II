package br.edu.ifsuldeminas.mch.webii.crudmanager.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "workshops")

public class Workshop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "O nome da oficina não pode ser vazio!")
	private String workshopName;
	
	@NotNull
	@Positive
	private Integer capacity;
	
	private Set<String> servicesProvided;
	
	@OneToOne(optional = false)
	private Address address;
	
	@OneToMany(mappedBy = "workshop")
    private List<Mechanic> mechanics;
	
	public Workshop() {
		
	}
	
	public Workshop(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWorkshopName() {
		return workshopName;
	}

	public void setWorkshopName(String workshopName) {
		this.workshopName = workshopName;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Set<String> getServicesProvided() {
		return servicesProvided;
	}

	public void setServicesProvided(Set<String> servicesProvided) {
		this.servicesProvided = servicesProvided;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Mechanic> getMechanics() {
		return mechanics;
	}

	public void setMechanics(List<Mechanic> mechanics) {
		this.mechanics = mechanics;
	}
}
