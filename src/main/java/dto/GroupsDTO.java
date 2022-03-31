package dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.nagarro.student.lms.entity.Groups;
import com.nagarro.student.lms.entity.Students;
import com.nagarro.student.lms.entity.Teachers;

public class GroupsDTO {

	private Integer groupId;
	private String name;
	private Set<StudentsDTO> students;
	private Set<TeachersDTO> teachers;

	public Groups toEntity() {
		Groups g = new Groups();
		g.setName(this.getName());
		g.setGroupId(this.getGroupId());
		if (this.students != null) {
			Set<Students> studentsSet = this.students.parallelStream().map(t -> t.toEntity()).collect(Collectors.toSet());
			g.setStudents(studentsSet);
		}
		if (this.teachers != null) {
			Set<Teachers> teachersSet = this.teachers.parallelStream().map(t -> t.toEntity()).collect(Collectors.toSet());
			g.setTeachers(teachersSet);
		}
		return g;
	}

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

	public Set<StudentsDTO> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentsDTO> students) {
		this.students = students;
	}

	public Set<TeachersDTO> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<TeachersDTO> teachers) {
		this.teachers = teachers;
	}

}
