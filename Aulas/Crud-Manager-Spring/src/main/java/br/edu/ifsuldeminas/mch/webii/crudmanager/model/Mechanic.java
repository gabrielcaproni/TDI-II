package br.edu.ifsuldeminas.mch.webii.crudmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "mechanics")
public class Mechanic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "Nome do mecânico não pode ser vazio!")
	private String name;
	
	@NotBlank(message = "Especialidade não pode ser vazia!")
	private String specialty;
	
	@NotBlank(message = "Horário de atendimento não pode ser vazio!")
	private String openingHours;

	@NotBlank(message = "Número de contato não pode ser vazio!")
	private String phone;
	
	@ManyToOne(optional = false)
	private Workshop workshop;

	public Mechanic() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialty() {
		return specialty;
	}
	
	public void setSpecialty (String specialty) {
		this.specialty = specialty;
	}
	

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Workshop getWorkshop() {
		return workshop;
	}

	public void setWorkshop(Workshop workshop) {
		this.workshop = workshop;
	}	
}
