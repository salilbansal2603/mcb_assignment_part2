package com.nagarro.student.lms.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.student.lms.entity.Marks;
import com.nagarro.student.lms.entity.Students;
import com.nagarro.student.lms.entity.Subjects;
import com.nagarro.student.lms.exception.RecordNotFoundException;
import com.nagarro.student.lms.repository.MarksRepository;
import com.nagarro.student.lms.repository.StudentsRepository;
import com.nagarro.student.lms.repository.SubjectsRepository;
import com.nagarro.student.lms.service.MarksService;

import dto.MarksDTO;
import dto.StudentSubjectMarks;
import dto.StudentsDTO;
import dto.SubjectsDTO;

@Service
public class MarksServiceImpl implements MarksService {

	@Autowired
	private MarksRepository marksRepository;

	@Autowired
	private StudentsRepository studentRepository;

	@Autowired
	private SubjectsRepository subjectsRepository;

	@Override
	public MarksDTO saveOrUpdate(MarksDTO marks) {
		Marks marksObj = null;
		if (marks.getMarkId() != null) {
			Optional<Marks> mark = marksRepository.findById(marks.getMarkId());
			if (mark.isPresent()) {
				Marks markO = mark.get();
				if (marks.getDate() != null) {
					markO.setDate(marks.getDate());
				}
				if (marks.getMark() != null) {
					markO.setMark(marks.getMark());
				}
				if (marks.getStudent() != null) {
					markO.setStudent(marks.getStudent().toEntity());
				}
				if (marks.getSubject() != null) {
					markO.setSubject(marks.getSubject().toEntity());
				}
				marksObj = marksRepository.save(markO);
			} else {
				throw new RecordNotFoundException("No Marks Present with this ID");
			}
		} else {
			marksObj = marksRepository.save(marks.toEntity());
		}
		return marksObj.toDTO();
	}

	@Override
	public List<MarksDTO> getAllMarks() {
		return marksRepository.findAll().parallelStream().map(t -> t.toDTO()).collect(Collectors.toList());
	}

	@Override
	public MarksDTO getMarksById(int id) throws RecordNotFoundException {
		Optional<Marks> marks = marksRepository.findById(id);
		if (marks.isPresent()) {
			return marks.get().toDTO();
		} else {
			throw new RecordNotFoundException("No Marks Present with this ID");
		}
	}

	@Override
	public void delete(int id) {
		marksRepository.deleteById(id);
	}

	@Override
	public List<MarksDTO> getMarksByStudentID(Integer id) {
		return marksRepository.findAllByStudent(id).parallelStream().map(t -> t.toDTO()).collect(Collectors.toList());
	}

	@Override
	public StudentSubjectMarks getMarksPerSubjectByStudent(Integer id) throws RecordNotFoundException {
		Optional<Students> student = studentRepository.findById(id);
		if (student.isPresent()) {
			StudentsDTO studentDTO = student.get().toDTO();
			StudentSubjectMarks result = new StudentSubjectMarks();
			result.setStudent(studentDTO);

			List<Subjects> subjects = subjectsRepository.getSubjectsByStudent(id);
			List<SubjectsDTO> subjectDTOs = subjects.parallelStream().map(t -> t.toDTO()).collect(Collectors.toList());
			result.setSubjects(subjectDTOs);

			return result;
		} else {
			throw new RecordNotFoundException("No Student Present with this ID");
		}
	}

}
