package com.jrp.pma.services;

import java.util.List;
import java.util.Optional;

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
	
	public Optional<Project> findById(Long id) {
		return projRepo.findById(id);
	}
	
	public List<ChartData> getProjectStatus() {
		
		return projRepo.getProjectStatus();
	}
	
	public void deleteById(Long id) {
		projRepo.deleteById(id);
	}
	
	public void delete(Project project) {
		projRepo.delete(project);
	}
	
	public Project findByProjectId(Long id) {
		return projRepo.findByProjectId(id);
	}
}
