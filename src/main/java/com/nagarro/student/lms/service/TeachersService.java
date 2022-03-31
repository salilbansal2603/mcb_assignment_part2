package com.nagarro.student.lms.service;

import java.util.List;

import com.nagarro.student.lms.exception.RecordNotFoundException;

import dto.TeachersDTO;

public interface TeachersService {
	public TeachersDTO saveOrUpdate(TeachersDTO teachers);

	public List<TeachersDTO> getAllTeachers();

	public TeachersDTO getTeachersById(int id) throws RecordNotFoundException;

	public void delete(int id);

}
