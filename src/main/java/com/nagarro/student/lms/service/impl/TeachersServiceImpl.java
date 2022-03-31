package com.nagarro.student.lms.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.student.lms.entity.Teachers;
import com.nagarro.student.lms.exception.RecordNotFoundException;
import com.nagarro.student.lms.repository.TeachersRepository;
import com.nagarro.student.lms.service.TeachersService;

import dto.TeachersDTO;

@Service
public class TeachersServiceImpl implements TeachersService {

	@Autowired
	private TeachersRepository teachersRepository;

	@Override
	public TeachersDTO saveOrUpdate(TeachersDTO teachers) {
		Teachers teachersObj = null;
		if (teachers.getTeachersId() != null) {
			Optional<Teachers> teacher = teachersRepository.findById(teachers.getTeachersId());
			if (teacher.isPresent()) {
				Teachers teacherO = teacher.get();
				if (teachers.getGroup() != null) {
					teacherO.setGroup(teachers.getGroup().toEntity());
				}
				if (teachers.getSubject() != null) {
					teacherO.setSubject(teachers.getSubject().toEntity());
				}
				teachersObj = teachersRepository.save(teacherO);
			} else {
				throw new RecordNotFoundException("No Teacger Present with this ID");
			}
		} else {
			teachersObj = teachersRepository.save(teachers.toEntity());
		}
		return teachersObj.toDTO();
	}

	@Override
	public List<TeachersDTO> getAllTeachers() {
		return teachersRepository.findAll().parallelStream().map(t -> t.toDTO()).collect(Collectors.toList());
	}

	@Override
	public TeachersDTO getTeachersById(int id) throws RecordNotFoundException {
		Optional<Teachers> teacher = teachersRepository.findById(id);
		if (teacher.isPresent()) {
			return teacher.get().toDTO();
		} else {
			throw new RecordNotFoundException("No Teacher Present with this ID");
		}
	}

	@Override
	public void delete(int id) {
		teachersRepository.deleteById(id);

	}
}
