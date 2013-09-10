package com.formation.jee.domain;

import java.util.Date;

public class Computer {

	private int id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private int company_id;
	
	public Computer() {
		
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

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	public static class Builder {
		private Computer computer;
		
		public Builder() {
			computer = new Computer();
		}
		
		public Builder id(int id) {
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
		
		public Builder company_id(int company_id) {
			computer.setCompany_id(company_id);
			return this;
		}
		
		
		
		public Computer build() {
			return computer;
		}
	}

}
