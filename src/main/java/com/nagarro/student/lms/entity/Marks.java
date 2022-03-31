package com.nagarro.student.lms.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import dto.MarksDTO;
import dto.StudentsDTO;
import dto.SubjectsDTO;

@Entity
public class Marks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer markId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id", nullable = false)
	private Students student;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subject_id", nullable = false)
	private Subjects subject;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "mark")
	private Integer mark;

	public Integer getMarkId() {
		return markId;
	}

	public void setMarkId(Integer markId) {
		this.markId = markId;
	}

	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}

	public Subjects getSubject() {
		return subject;
	}

	public void setSubject(Subjects subject) {
		this.subject = subject;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public MarksDTO toDTO() {
		MarksDTO m = new MarksDTO();
		m.setMarkId(this.getMarkId());
		m.setDate(this.getDate());
		m.setMark(this.getMark());
		if (this.subject != null) {
			SubjectsDTO s = new SubjectsDTO();
			s.setSubjectId(this.subject.getSubjectId());
			s.setTitle(this.subject.getTitle());
			m.setSubject(s);
		}
		if (this.student != null) {
			StudentsDTO s = new StudentsDTO();
			s.setFirstName(this.student.getFirstName());
			s.setLastName(this.student.getLastName());
			s.setStudentId(this.student.getStudentId());
			m.setStudent(s);
		}
		return m;
	}

}
