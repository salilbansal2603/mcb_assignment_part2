package com.nagarro.student.lms.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import dto.MarksDTO;
import dto.SubjectsDTO;
import dto.TeachersDTO;

@Entity
public class Subjects {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer subjectId;

	@Column(name = "title")
	private String title;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
	private Set<Marks> marks;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
	private Set<Teachers> teachers;

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Marks> getMarks() {
		return marks;
	}

	public void setMarks(Set<Marks> marks) {
		this.marks = marks;
	}

	public Set<Teachers> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teachers> teachers) {
		this.teachers = teachers;
	}

	public SubjectsDTO toDTO() {
		SubjectsDTO s = new SubjectsDTO();
		s.setSubjectId(this.getSubjectId());
		s.setTitle(this.getTitle());
		if(this.marks != null) {
			Set<MarksDTO> marksSet = new HashSet<MarksDTO>();
			for (Marks mark : this.marks) {
				marksSet.add(mark.toDTO());
			}
			s.setMarks(marksSet);
		}
		if(this.teachers != null) {
			Set<TeachersDTO> teachersSet = new HashSet<TeachersDTO>();
			for (Teachers teacher : this.teachers) {
				teachersSet.add(teacher.toDTO());
			}
			s.setTeachers(teachersSet);
		}
		return s;
	}
}
