package com.jrp.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.entities.Project;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projRepo;
	
	public Project save(Project project) {
		
		return projRepo.save(project);
	}
	
	public List<Project> getAll() {
		
		return projRepo.findAll(); 
	}
	
	public List<ChartData> getProjectStatus() {
		
		return projRepo.getProjectStatus();
	}
}
