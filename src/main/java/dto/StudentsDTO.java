package dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.nagarro.student.lms.entity.Marks;
import com.nagarro.student.lms.entity.Students;

public class StudentsDTO {
	private Integer studentId;
	private GroupsDTO group;
	private String firstName;
	private String lastName;
	private Set<MarksDTO> marks;
	
	public Students toEntity() {
		Students s = new Students();
		s.setFirstName(this.getFirstName());
		s.setLastName(this.getLastName());
		s.setStudentId(this.getStudentId());
		if(this.getGroup() != null) {
			s.setGroup(this.getGroup().toEntity());
		}
		if(this.getMarks() != null) {
			Set<Marks> markEntities = this.marks.parallelStream().map(t -> t.toEntity()).collect(Collectors.toSet());
			s.setMarks(markEntities);
		}
		return s;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public GroupsDTO getGroup() {
		return group;
	}

	public void setGroup(GroupsDTO group) {
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

	public Set<MarksDTO> getMarks() {
		return marks;
	}

	public void setMarks(Set<MarksDTO> marks) {
		this.marks = marks;
	}

}
