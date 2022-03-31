package com.nagarro.student.lms.service;

import java.util.List;

import com.nagarro.student.lms.exception.RecordNotFoundException;

import dto.StudentCount;
import dto.StudentsDTO;

public interface StudentsService {

	public StudentsDTO saveOrUpdate(StudentsDTO students);

	public List<StudentsDTO> getAllStudents();

	public StudentsDTO getStudentsById(int id) throws RecordNotFoundException;

	public void delete(int id);
	
	public StudentCount getStudentCountByTeacher(int id) throws RecordNotFoundException;

}
