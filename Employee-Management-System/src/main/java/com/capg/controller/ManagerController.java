package com.capg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.entity.Manager;
import com.capg.repository.ManagerRepository;
import com.capg.service.ManagementService;
@RestController
@RequestMapping("/managers")
public class ManagerController {
	@Autowired
	private ManagementService managementService;
	
	@PostMapping("/save-manager")
	public Manager addManager(@RequestBody Manager manager) {
		return  managementService.saveManager(manager);
	}
}
