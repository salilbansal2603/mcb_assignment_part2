package com.nagarro.student.lms.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.student.lms.entity.Subjects;
import com.nagarro.student.lms.exception.RecordNotFoundException;
import com.nagarro.student.lms.repository.SubjectsRepository;
import com.nagarro.student.lms.service.SubjectsService;

import dto.SubjectsDTO;

@Service
public class SubjectsServiceImpl implements SubjectsService {

	@Autowired
	private SubjectsRepository subjectsRepository;

	@Override
	public SubjectsDTO saveOrUpdate(SubjectsDTO subjects) {
		Subjects subjectsObj = null;
		if (subjects.getSubjectId() != null) {
			Optional<Subjects> subject = subjectsRepository.findById(subjects.getSubjectId());
			if (subject.isPresent()) {
				Subjects subjectO = subject.get();
				if (subjects.getTitle() != null) {
					subjectO.setTitle(subjects.getTitle());
				}
				subjectsObj = subjectsRepository.save(subjectO);
			} else {
				throw new RecordNotFoundException("No Subject Present with this ID");
			}
		} else {
			subjectsObj = subjectsRepository.save(subjects.toEntity());
		}
		return subjectsObj.toDTO();
	}

	@Override
	public List<SubjectsDTO> getAllSubjects() {
		return subjectsRepository.findAll().parallelStream().map(t -> t.toDTO())
				.collect(Collectors.toList());
	}

	@Override
	public SubjectsDTO getSubjectsById(int id) throws RecordNotFoundException {
		Optional<Subjects> subject = subjectsRepository.findById(id);
		if (subject.isPresent()) {
			return subject.get().toDTO();
		} else {
			throw new RecordNotFoundException("No Subject Present with this ID");
		}
	}

	@Override
	public void delete(int id) {
		subjectsRepository.deleteById(id);

	}

}
