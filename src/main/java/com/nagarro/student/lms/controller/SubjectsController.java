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
import com.nagarro.student.lms.service.SubjectsService;

import dto.SubjectsDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
public class SubjectsController {
	@Autowired
	private SubjectsService subjectsService;

	@PostMapping("/subjects")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<SubjectsDTO> saveSubjects(@RequestBody SubjectsDTO subjectsDTO) {
		SubjectsDTO gt = subjectsService.saveOrUpdate(subjectsDTO);
		if (gt != null) {
			return new ResponseEntity<SubjectsDTO>(gt, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<SubjectsDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/subjects")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<SubjectsDTO> updateSubjects(@RequestBody SubjectsDTO subjectsDTO) {
		SubjectsDTO gt = subjectsService.saveOrUpdate(subjectsDTO);
		if (gt != null) {
			return new ResponseEntity<SubjectsDTO>(gt, HttpStatus.OK);
		} else {
			return new ResponseEntity<SubjectsDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/subjects")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<List<SubjectsDTO>> getAllSubjects() {
		return new ResponseEntity<List<SubjectsDTO>>(subjectsService.getAllSubjects(), HttpStatus.OK);
	}

	@GetMapping("/subjects/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<?> getSubjectsByID(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<SubjectsDTO>(subjectsService.getSubjectsById(id), HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/subjects/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private void deleteSubjectsByID(@PathVariable("id") Integer id) {
		subjectsService.delete(id);
	}

}
