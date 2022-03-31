package com.nagarro.student.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.student.lms.exception.RecordNotFoundException;
import com.nagarro.student.lms.service.GroupsService;

import dto.GroupsDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
public class GroupsController {
	@Autowired
	private GroupsService groupsService;

	@PostMapping("/groups")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<GroupsDTO> saveGroups(@RequestBody GroupsDTO groupsDTO) {
		GroupsDTO gt = groupsService.saveOrUpdate(groupsDTO);
		if (gt != null) {
			return new ResponseEntity<GroupsDTO>(gt, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<GroupsDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/groups")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<GroupsDTO> updateGroups(@RequestBody GroupsDTO groupsDTO) {
		GroupsDTO gt = groupsService.saveOrUpdate(groupsDTO);
		if (gt != null) {
			return new ResponseEntity<GroupsDTO>(gt, HttpStatus.OK);
		} else {
			return new ResponseEntity<GroupsDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/groups")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<List<GroupsDTO>> getAllGroups() {
		return new ResponseEntity<List<GroupsDTO>>(groupsService.getAllGroups(), HttpStatus.OK);
	}

	@GetMapping("/groups/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<?> getGroupsByID(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<GroupsDTO>(groupsService.getGroupsById(id), HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/groups/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private void deleteGroupsByID(@PathVariable("id") Integer id) {
		groupsService.delete(id);
	}

}
