package dto;

import com.nagarro.student.lms.entity.Teachers;

public class TeachersDTO {

	private Integer teachersId;
	private GroupsDTO group;
	private SubjectsDTO subject;
	
	public Teachers toEntity() {
		Teachers t = new Teachers();
		t.setTeachersId(this.getTeachersId());
		t.setGroup(this.group.toEntity());
		t.setSubject(this.subject.toEntity());
		return t;
	}

	public Integer getTeachersId() {
		return teachersId;
	}

	public void setTeachersId(Integer teachersId) {
		this.teachersId = teachersId;
	}

	public GroupsDTO getGroup() {
		return group;
	}

	public void setGroup(GroupsDTO group) {
		this.group = group;
	}

	public SubjectsDTO getSubject() {
		return subject;
	}

	public void setSubject(SubjectsDTO subject) {
		this.subject = subject;
	}

}
