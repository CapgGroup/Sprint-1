package com.capg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capg.entity.Manager;
import com.capg.repository.ManagerRepository;
@RestController
public class ManagerController {
	
	@Autowired
	private ManagerRepository managerRpository;
	
	@PostMapping("/add-manager")
	public Manager addManager(@RequestBody Manager manager) {
		return  managerRpository.save(manager);
	}
}
