package com.nagarro.student.lms.service;

import java.util.List;

import com.nagarro.student.lms.exception.RecordNotFoundException;

import dto.SubjectsDTO;

public interface SubjectsService {
	public SubjectsDTO saveOrUpdate(SubjectsDTO subjects);

	public List<SubjectsDTO> getAllSubjects();

	public SubjectsDTO getSubjectsById(int id) throws RecordNotFoundException;

	public void delete(int id);
}
