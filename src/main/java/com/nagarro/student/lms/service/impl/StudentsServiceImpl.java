package com.nagarro.student.lms.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.student.lms.entity.Students;
import com.nagarro.student.lms.entity.Teachers;
import com.nagarro.student.lms.exception.RecordNotFoundException;
import com.nagarro.student.lms.repository.StudentsRepository;
import com.nagarro.student.lms.repository.TeachersRepository;
import com.nagarro.student.lms.service.StudentsService;

import dto.StudentCount;
import dto.StudentsDTO;
import dto.TeachersDTO;

@Service
public class StudentsServiceImpl implements StudentsService {

	@Autowired
	private StudentsRepository studentsRepository;

	@Autowired
	private TeachersRepository teachersRepository;

	@Override
	public StudentsDTO saveOrUpdate(StudentsDTO students) {
		Students studentsObj = null;
		if (students.getStudentId() != null) {
			Optional<Students> student = studentsRepository.findById(students.getStudentId());
			if (student.isPresent()) {
				Students studentO = student.get();
				if (students.getFirstName() != null) {
					studentO.setFirstName(students.getFirstName());
				}
				if (students.getLastName() != null) {
					studentO.setLastName(students.getLastName());
				}
				if (students.getGroup() != null) {
					studentO.setGroup(students.getGroup().toEntity());
				}
				studentsObj = studentsRepository.save(studentO);
			} else {
				throw new RecordNotFoundException("No Student Present with this ID");
			}
		} else {
			studentsObj = studentsRepository.save(students.toEntity());
		}
		return studentsObj.toDTO();
	}

	@Override
	public List<StudentsDTO> getAllStudents() {
		return studentsRepository.findAll().parallelStream().map(t -> t.toDTO()).collect(Collectors.toList());
	}

	@Override
	public StudentsDTO getStudentsById(int id) throws RecordNotFoundException {
		Optional<Students> students = studentsRepository.findById(id);
		if (students.isPresent()) {
			return students.get().toDTO();
		} else {
			throw new RecordNotFoundException("No Student Present with this ID");
		}
	}

	@Override
	public void delete(int id) {
		studentsRepository.deleteById(id);

	}

	@Override
	public StudentCount getStudentCountByTeacher(int id) throws RecordNotFoundException {
		Optional<Teachers> teacher = teachersRepository.findById(id);
		if (teacher.isPresent()) {
			int studentCount = studentsRepository.findStudentCountByTeacher(id);
			TeachersDTO teachersDTO = teacher.get().toDTO();

			StudentCount st = new StudentCount();
			st.setTeacher(teachersDTO);
			st.setStudentCount(studentCount);
			return st;
		} else {
			throw new RecordNotFoundException("No Teacher Present with this ID");
		}
	}

}
