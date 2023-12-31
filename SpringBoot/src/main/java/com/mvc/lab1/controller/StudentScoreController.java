package com.mvc.lab1.controller;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.javafaker.Faker;
import com.mvc.lab1.entity.StudentScore;
import com.mvc.lab1.repository.StudentScoreRepository;

@Controller
@RequestMapping("/student_score")
public class StudentScoreController {
	@Autowired
	private StudentScoreRepository studentScoreRepository;
	
	@GetMapping("/add")
	@ResponseBody
	public String add() {
		Faker faker = new Faker();
		Random random = new Random();
		
		String name = faker.name().fullName(); // 隨機生成名字
		Integer chineseScore = random.nextInt(101);
		Integer englishScore = random.nextInt(101);
		Integer mathScore = random.nextInt(101);
		
		StringBuilder output = new StringBuilder(); 
		StudentScore studentScore = new StudentScore(name, chineseScore, englishScore, mathScore);
		output.append(studentScore);
		
		// 儲存到資料表中
		studentScoreRepository.save(studentScore);
		output.append(studentScore);
		
		return output.toString();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public String get(@PathVariable("id") Integer id) {
		Optional<StudentScore> studentScoreOpt = studentScoreRepository.findById(id);
		if(studentScoreOpt.isPresent()) {
			return studentScoreOpt.get().toString();
		} else {
			return "No data !";
		}
	}
	
	@PutMapping("/updateName/{id}")
	@ResponseBody
	public String updateName(@PathVariable("id") Integer id, @RequestParam("name") String name) {
		studentScoreRepository.updateNameById(id, name);
		return "Update OK";
	}
}
