package com.nagarro.student.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nagarro.student.lms.entity.Marks;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Integer> {
	
	@Query("SELECT m FROM Marks m WHERE m.student.studentId=:studentID ")
	List<Marks> findAllByStudent(@Param("studentID") Integer studentID);
}
