package dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.nagarro.student.lms.entity.Marks;
import com.nagarro.student.lms.entity.Subjects;
import com.nagarro.student.lms.entity.Teachers;

public class SubjectsDTO {
	private Integer subjectId;
	private String title;
	private Set<MarksDTO> marks;
	private Set<TeachersDTO> teachers;

	public Subjects toEntity() {
		Subjects s = new Subjects();
		s.setSubjectId(this.getSubjectId());
		s.setTitle(this.getTitle());
		if (this.marks != null) {
			Set<Marks> marksSet = this.marks.parallelStream().map(t -> t.toEntity()).collect(Collectors.toSet());
			s.setMarks(marksSet);
		}
		if (this.teachers != null) {
			Set<Teachers> teachersSet = this.teachers.parallelStream().map(t -> t.toEntity()).collect(Collectors.toSet());
			s.setTeachers(teachersSet);
		}
		return s;
	}

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

	public Set<MarksDTO> getMarks() {
		return marks;
	}

	public void setMarks(Set<MarksDTO> marks) {
		this.marks = marks;
	}

	public Set<TeachersDTO> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<TeachersDTO> teachers) {
		this.teachers = teachers;
	}

}
