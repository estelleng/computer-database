package com.formation.jee.service;

import java.util.List;

import com.formation.jee.domain.Computer;

public interface ComputerService {

	public abstract List<Computer> getComputers();

	public abstract void create(Computer computer);

}