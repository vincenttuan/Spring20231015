package com.mvc.lab1.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.lab1.entity.StudentScore;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Integer> {
	// 只要修改名字
	@Transactional
	@Modifying
	@Query("UPDATE StudentScore s SET s.name = :name WHERE s.id = :id")
	void updateNameById(Integer id, String name);
}
