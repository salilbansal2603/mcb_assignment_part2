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
import com.nagarro.student.lms.service.TeachersService;

import dto.TeachersDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
public class TeachersController {
	@Autowired
	private TeachersService teachersService;

	@PostMapping("/teachers")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<TeachersDTO> saveTeachers(@RequestBody TeachersDTO teachersDTO) {
		TeachersDTO gt = teachersService.saveOrUpdate(teachersDTO);
		if (gt != null) {
			return new ResponseEntity<TeachersDTO>(gt, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<TeachersDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/teachers")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<TeachersDTO> updateTeachers(@RequestBody TeachersDTO teachersDTO) {
		TeachersDTO gt = teachersService.saveOrUpdate(teachersDTO);
		if (gt != null) {
			return new ResponseEntity<TeachersDTO>(gt, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<TeachersDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/teachers")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<List<TeachersDTO>> getAllTeachers() {
		return new ResponseEntity<List<TeachersDTO>>(teachersService.getAllTeachers(), HttpStatus.OK);
	}

	@GetMapping("/teachers/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<?> getTeachersByID(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<TeachersDTO>(teachersService.getTeachersById(id), HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/teachers/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private void deleteTeachersByID(@PathVariable("id") Integer id) {
		teachersService.delete(id);
	}

}
