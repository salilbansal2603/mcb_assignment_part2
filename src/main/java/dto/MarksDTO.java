package dto;

import java.time.LocalDate;

import com.nagarro.student.lms.entity.Marks;

public class MarksDTO {

	private Integer markId;
	private StudentsDTO student;
	private SubjectsDTO subject;
	private LocalDate date;
	private Integer mark;
	
	public Marks toEntity() {
		Marks m = new Marks();
		m.setMarkId(this.getMarkId());
		m.setDate(this.getDate());
		m.setMark(this.getMark());
		if(this.getStudent() != null) {
			m.setStudent(this.getStudent().toEntity());
		}
		if(this.getSubject() != null) {
			m.setSubject(this.getSubject().toEntity());
		}
		return m;
	}

	public Integer getMarkId() {
		return markId;
	}

	public void setMarkId(Integer markId) {
		this.markId = markId;
	}

	public StudentsDTO getStudent() {
		return student;
	}

	public void setStudent(StudentsDTO student) {
		this.student = student;
	}

	public SubjectsDTO getSubject() {
		return subject;
	}

	public void setSubject(SubjectsDTO subject) {
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

}
