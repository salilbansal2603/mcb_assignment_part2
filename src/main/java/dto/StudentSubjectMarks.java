package dto;

import java.util.List;

public class StudentSubjectMarks {
	private StudentsDTO student;
	private List<SubjectsDTO> subjects;

	public List<SubjectsDTO> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectsDTO> subjects) {
		this.subjects = subjects;
	}

	public StudentsDTO getStudent() {
		return student;
	}

	public void setStudent(StudentsDTO student) {
		this.student = student;
	}

}
