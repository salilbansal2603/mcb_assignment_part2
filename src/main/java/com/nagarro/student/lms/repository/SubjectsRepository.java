package com.nagarro.student.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nagarro.student.lms.entity.Subjects;

@Repository
public interface SubjectsRepository extends  JpaRepository<Subjects, Integer> {

	@Query("SELECT s FROM Subjects s INNER JOIN Marks m "
			+ "ON m.subject.subjectId=s.subjectId AND m.student.studentId=:studentID ")
	List<Subjects> getSubjectsByStudent(@Param("studentID") Integer studentID);

}
