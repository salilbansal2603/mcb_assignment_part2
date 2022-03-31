package dto;

public class StudentCount {
	private int studentCount;
	private TeachersDTO teacher;

	public int getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}

	public TeachersDTO getTeacher() {
		return teacher;
	}

	public void setTeacher(TeachersDTO teacher) {
		this.teacher = teacher;
	}

}
