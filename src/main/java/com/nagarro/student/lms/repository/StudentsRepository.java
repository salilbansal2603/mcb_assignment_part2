package com.nagarro.student.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nagarro.student.lms.entity.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Integer>  {
	
	@Query("SELECT count(s) FROM Students s INNER JOIN Groups g ON s.group.groupId=g.groupId "
			+ "INNER JOIN Teachers t ON g.groupId=t.group.groupId "
			+ "WHERE t.teachersId= :teacherID ")
	int findStudentCountByTeacher(@Param("teacherID") Integer teacherID);

}
