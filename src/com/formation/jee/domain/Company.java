package com.formation.jee.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")

public class Company {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="name")
	private String name;

	public Company() {

	}
	
	//----------------Accesseurs et mutateurs-------------------------
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Classe qui va permettre de faciliter la cr�ation d'instances de "Company"
	 * @author Roxanne
	 *
	 */

	public static class Builder {
		private Company company;

		public Builder() {
			company = new Company();
		}

		public Builder id(long id) {
			company.setId(id);
			return this;
		}

		public Builder name(String name) {
			company.setName(name);
			return this;
		}

		public Company build() {
			return company;
		}
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}
}
