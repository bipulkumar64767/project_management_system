package com.pms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.pms.entity.pmsEntity;
import com.pms.services.pmsServices;

@RestController
public class pmsController {
	
	@Autowired
	pmsServices pmsService;
	
	
	@PostMapping("/addProject")
	public ResponseEntity<?> addProject(@RequestBody pmsEntity entity)
	{	
		return pmsService.addProject(entity);
	}
	
	
	@PutMapping("/updateProjectByPmdId/{pmdId}")
	public ResponseEntity<?> updateBypmdId(@PathVariable Integer pmdId,@RequestBody pmsEntity entityobj)
	{
		return pmsService.updateProjectById(pmdId,entityobj);
	}
	
	
	@DeleteMapping("/deleteByPmdId/{pmdId}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id)
	{
		return pmsService.deleteProjectById(id);
	}
	
	
	@GetMapping("/searchProjectById/{pmdId}")
	public ResponseEntity<?> searchById(@PathVariable int pmdId)
	{
		return pmsService.searchProjectBypmdId(pmdId);
	}
	
	@GetMapping("/seeAllProject")
	public ResponseEntity<?> seeAllProject()
	{
		return pmsService.findAllProjects();
	}
	
	
	@GetMapping("/searchByProjectName/{title}")
	public ResponseEntity<?> searchByProjectName(@PathVariable String title)
	{
		return pmsService.searchByProjectName(title);
	}
}
