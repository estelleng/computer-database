package com.formation.jee.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "computer")
public class Computer {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "introduced")
	private Date introduced;

	@Column(name = "discontinued")
	private Date discontinued;

	@ManyToOne
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	protected Company company;

	public Computer() {

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

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * Classe qui va permettre de faciliter la cr�ation d'instances de "Computer"
	 * @author Roxanne
	 *
	 */
	public static class Builder {
		private Computer computer;

		public Builder() {
			computer = new Computer();
		}

		public Builder id(long id) {
			computer.setId(id);
			return this;
		}

		public Builder name(String name) {
			computer.setName(name);
			return this;
		}

		public Builder introduced(Date introduced) {
			computer.setIntroduced(introduced);
			return this;
		}

		public Builder discontinued(Date discontinued) {
			computer.setDiscontinued(discontinued);
			return this;
		}

		public Builder company(Company company) {
			computer.setCompany(company);
			return this;
		}

		public Computer build() {
			return computer;
		}
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued
				+ ", company_name=" + company.getName() + "]";
	}

}
