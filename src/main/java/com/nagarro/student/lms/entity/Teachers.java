package com.nagarro.student.lms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import dto.GroupsDTO;
import dto.SubjectsDTO;
import dto.TeachersDTO;

@Entity
public class Teachers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer teachersId;

	@ManyToOne
	@JoinColumn(name = "group_id", nullable = false)
	private Groups group;

	@ManyToOne
	@JoinColumn(name = "subject_id", nullable = false)
	private Subjects subject;

	public Integer getTeachersId() {
		return teachersId;
	}

	public void setTeachersId(Integer teachersId) {
		this.teachersId = teachersId;
	}

	public Groups getGroup() {
		return group;
	}

	public void setGroup(Groups group) {
		this.group = group;
	}

	public Subjects getSubject() {
		return subject;
	}

	public void setSubject(Subjects subject) {
		this.subject = subject;
	}

	public TeachersDTO toDTO() {
		TeachersDTO t = new TeachersDTO();
		t.setTeachersId(this.getTeachersId());
		if (this.group != null) {
			GroupsDTO g = new GroupsDTO();
			g.setGroupId(this.group.getGroupId());
			g.setName(this.group.getName());
			t.setGroup(g);
		}
		if (this.subject != null) {
			SubjectsDTO s = new SubjectsDTO();
			s.setSubjectId(this.subject.getSubjectId());
			s.setTitle(this.subject.getTitle());
			t.setSubject(s);
		}
		return t;
	}
}
