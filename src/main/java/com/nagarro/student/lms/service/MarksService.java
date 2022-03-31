package com.nagarro.student.lms.service;

import java.util.List;

import com.nagarro.student.lms.exception.RecordNotFoundException;

import dto.MarksDTO;
import dto.StudentSubjectMarks;

public interface MarksService {

	public MarksDTO saveOrUpdate(MarksDTO marks);

	public List<MarksDTO> getAllMarks();

	public MarksDTO getMarksById(int id) throws RecordNotFoundException;

	public void delete(int id);
	
	public List<MarksDTO> getMarksByStudentID(Integer id);

	public StudentSubjectMarks getMarksPerSubjectByStudent(Integer id) throws RecordNotFoundException;

}
