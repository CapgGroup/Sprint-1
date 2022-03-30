package com.capg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

}
