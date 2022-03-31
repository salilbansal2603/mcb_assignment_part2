package com.nagarro.student.lms.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import dto.GroupsDTO;
import dto.MarksDTO;
import dto.StudentsDTO;

@Entity
public class Students {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer studentId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "group_id", nullable = false)
	private Groups group;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	private Set<Marks> marks;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Groups getGroup() {
		return group;
	}

	public void setGroup(Groups group) {
		this.group = group;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Marks> getMarks() {
		return marks;
	}

	public void setMarks(Set<Marks> marks) {
		this.marks = marks;
	}
	
	public StudentsDTO toDTO() {
		StudentsDTO s = new StudentsDTO();
		s.setFirstName(this.getFirstName());
		s.setLastName(this.getLastName());
		s.setStudentId(this.getStudentId());
		if(this.marks != null) {
			Set<MarksDTO> marksSet = new HashSet<MarksDTO>();
			for (Marks mark : this.marks) {
				marksSet.add(mark.toDTO());
			}
			s.setMarks(marksSet);
		}
		if (this.group != null) {
			GroupsDTO g = new GroupsDTO();
			g.setGroupId(this.group.getGroupId());
			g.setName(this.group.getName());
			s.setGroup(g);
		}
		return s;
	}

}
