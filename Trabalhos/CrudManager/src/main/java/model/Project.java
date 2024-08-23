package model;

import java.util.Date;

public class Project {
	
	private int id;
	private String name;
	private String description;
	private String start_date;
	private String end_date;
	private Company company;
	
	public Project(int id) {
		this.id = id;
	}
	
	
	public Project(int id, String name, String description, String start_date, String end_date, Company company) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.company = company;
	}

	public Project() {
		this(0);
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company companiesId) {
		this.company = companiesId;
	}
}
