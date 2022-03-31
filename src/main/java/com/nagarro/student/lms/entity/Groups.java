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

import dto.GroupsDTO;
import dto.StudentsDTO;
import dto.TeachersDTO;

@Entity
public class Groups {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer groupId;

	@Column(name = "name")
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
	private Set<Students> students;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
	private Set<Teachers> teachers;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Students> getStudents() {
		return students;
	}

	public void setStudents(Set<Students> students) {
		this.students = students;
	}

	public Set<Teachers> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teachers> teachers) {
		this.teachers = teachers;
	}
	
	public GroupsDTO toDTO() {
		GroupsDTO g = new GroupsDTO();
		g.setName(this.getName());
		g.setGroupId(this.getGroupId());
		if(this.students != null) {
			Set<StudentsDTO> studentsSet = new HashSet<StudentsDTO>();
			for (Students student : this.students) {
				studentsSet.add(student.toDTO());
			}
			g.setStudents(studentsSet);
		}
		if(this.teachers != null) {
			Set<TeachersDTO> teachersSet = new HashSet<TeachersDTO>();
			for (Teachers teacher : this.teachers) {
				teachersSet.add(teacher.toDTO());
			}
			g.setTeachers(teachersSet);
		}
		return g;
	}

}
