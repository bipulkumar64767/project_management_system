package com.pms.services;

import java.util.Optional;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pms.entity.pmsEntity;
import com.pms.repositories.PmsRepository;

@Service
public class pmsServices 
{
	
	@Autowired
	PmsRepository pmsrepo;
	

	public ResponseEntity<?> addProject(pmsEntity requestdto)
	{
		if(requestdto.getProjectId()!=null && requestdto.getProjectDescription()!=null && requestdto.getProjectName()!=null)
		{
			if(pmsrepo.checkProjectIdIsPresent(requestdto.getProjectId()) > 0)
			{
				return new ResponseEntity<>("Project with this id AlreadyPresent",HttpStatus.BAD_REQUEST);
			}else
			{
				pmsrepo.save(requestdto);
				return new ResponseEntity<>("Inserted Sucessfully....",HttpStatus.ACCEPTED);
			}
		}else
		{
			return new ResponseEntity<>("Please provide all the feilds",HttpStatus.PARTIAL_CONTENT);
		}
	}
	
	
	
	public ResponseEntity<?> updateProjectById(Integer pmdId,pmsEntity requestdto)
	{
		Optional<pmsEntity> project = pmsrepo.findById(pmdId);
		if(project.isPresent())
		{
			pmsEntity e1 = new pmsEntity();
			e1.setPmdId(project.get().getPmdId());
			if(requestdto.getProjectDescription()!=null)
			{
				e1.setProjectDescription(requestdto.getProjectDescription());
			}else
			{
				e1.setProjectDescription(project.get().getProjectDescription());
			}
			if(requestdto.getProjectId()!=null)
			{
				e1.setProjectId(requestdto.getProjectId());
			}else
			{
				e1.setProjectId(project.get().getProjectId());
			}
			if(requestdto.getProjectSession()!=null)
			{
				e1.setProjectSession(requestdto.getProjectSession());
			}else
			{
				e1.setProjectSession(project.get().getProjectSession());
			}
			if(requestdto.getProjectName()!=null)
			{
				e1.setProjectName(requestdto.getProjectName());
			}else
			{
				e1.setProjectName(project.get().getProjectName());
			}
			if(requestdto.getStudetName()!=null)
			{
				e1.setStudetName(requestdto.getStudetName());
			}else
			{
				e1.setStudetName(project.get().getStudetName());
			}
			pmsrepo.save(e1);
			return new ResponseEntity<>("Project is updated...",HttpStatus.FOUND);
				
		}else {
			return new ResponseEntity<>("Project with this id is Not present",HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<?> deleteProjectById(Integer id)
	{
		if(pmsrepo.existsById(id))
		{
			pmsrepo.deleteById(id);
			return new ResponseEntity<>("Project with Id =[ "+id+" ] is deleted sucessfully..",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("Project with this Id is Not found",HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> searchProjectBypmdId(int pmdId)
	{
		Optional<pmsEntity> project = pmsrepo.findById(pmdId);
		if(project.isPresent())
		{
			return new ResponseEntity<>(project.get().toString(),HttpStatus.FOUND);
		}
		else
		{
			return new ResponseEntity<>("Project is not available with this PmdId",HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<?> findAllProjects()
	{
		List<pmsEntity> al = pmsrepo.findAll();
		if(al.size()<=0)
		{
			return new ResponseEntity<>("Not any Projects Added",HttpStatus.NOT_FOUND);
		}else
		{
			return new ResponseEntity<>(al,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<?> searchByProjectName(String name)
	{
		List<pmsEntity> al = pmsrepo.searchAllProjectByName(name);
		System.out.println(al.size());
		if(al.isEmpty())
		{
			return new ResponseEntity<>("Not any project with this name is found",HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<>(al,HttpStatus.OK);
		}
	}
	
	
}
