package com.nagarro.student.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.student.lms.entity.Teachers;

@Repository
public interface TeachersRepository extends  JpaRepository<Teachers, Integer>  {

}
