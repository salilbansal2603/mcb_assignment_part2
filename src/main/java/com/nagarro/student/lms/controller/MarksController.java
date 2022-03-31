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
import com.nagarro.student.lms.service.MarksService;

import dto.MarksDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
public class MarksController {

	@Autowired
	private MarksService marksService;

	@PostMapping("/marks")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<MarksDTO> saveMarks(@RequestBody MarksDTO marksDTO) {
		MarksDTO gt = marksService.saveOrUpdate(marksDTO);
		if (gt != null) {
			return new ResponseEntity<MarksDTO>(gt, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<MarksDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/marks")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<MarksDTO> updateMarks(@RequestBody MarksDTO marksDTO) {
		MarksDTO gt = marksService.saveOrUpdate(marksDTO);
		if (gt != null) {
			return new ResponseEntity<MarksDTO>(gt, HttpStatus.OK);
		} else {
			return new ResponseEntity<MarksDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/marks")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<List<MarksDTO>> getAllMarks() {
		return new ResponseEntity<List<MarksDTO>>(marksService.getAllMarks(), HttpStatus.OK);
	}

	@GetMapping("/marks/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private ResponseEntity<?> getMarksByID(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<MarksDTO>(marksService.getMarksById(id), HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/marks/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	private void deleteMarksByID(@PathVariable("id") Integer id) {
		marksService.delete(id);
	}
}
