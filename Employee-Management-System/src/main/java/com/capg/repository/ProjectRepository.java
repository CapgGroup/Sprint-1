package com.capg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
