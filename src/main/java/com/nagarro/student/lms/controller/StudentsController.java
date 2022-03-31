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
import com.nagarro.student.lms.service.StudentsService;

import dto.StudentsDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
public class StudentsController {
	@Autowired
	private StudentsService studentsService;

	@PostMapping("/students")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<StudentsDTO> saveStudents(@RequestBody StudentsDTO studentsDTO) {
		StudentsDTO gt = studentsService.saveOrUpdate(studentsDTO);
		if (gt != null) {
			return new ResponseEntity<StudentsDTO>(gt, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<StudentsDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/students")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<StudentsDTO> updateStudents(@RequestBody StudentsDTO studentsDTO) {
		StudentsDTO gt = studentsService.saveOrUpdate(studentsDTO);
		if (gt != null) {
			return new ResponseEntity<StudentsDTO>(gt, HttpStatus.OK);
		} else {
			return new ResponseEntity<StudentsDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/students")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<List<StudentsDTO>> getAllStudents() {
		return new ResponseEntity<List<StudentsDTO>>(studentsService.getAllStudents(), HttpStatus.OK);
	}

	@GetMapping("/students/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<?> getStudentsByID(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<StudentsDTO>(studentsService.getStudentsById(id), HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/students/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private void deleteStudentsByID(@PathVariable("id") Integer id) {
		studentsService.delete(id);
	}
}
