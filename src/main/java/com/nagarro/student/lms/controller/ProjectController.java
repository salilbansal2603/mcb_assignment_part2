package com.nagarro.student.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.student.lms.exception.RecordNotFoundException;
import com.nagarro.student.lms.service.MarksService;
import com.nagarro.student.lms.service.StudentsService;

import dto.MarksDTO;
import dto.StudentCount;
import dto.StudentSubjectMarks;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
public class ProjectController {

	@Autowired
	private MarksService marksService;

	@Autowired
	private StudentsService studentsService;

	@GetMapping("marks-by-student/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	public ResponseEntity<List<MarksDTO>> getMarksByStudentId(@PathVariable("id") Integer id) {
		return new ResponseEntity<List<MarksDTO>>(marksService.getMarksByStudentID(id), HttpStatus.OK);
	}

	@GetMapping("student-count-by-teacher/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	public ResponseEntity<?> getStudentCountByTeacher(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<StudentCount>(studentsService.getStudentCountByTeacher(id), HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping("marks-per-subject-by-student/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	public ResponseEntity<?> getMarksPerSubjectByStudent(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<StudentSubjectMarks>(marksService.getMarksPerSubjectByStudent(id), HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
