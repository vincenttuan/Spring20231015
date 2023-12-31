package com.mvc.lab1.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
	
	@GetMapping("/")
	public String index(Model model) {
		List<StudentScore> scores = studentScoreRepository.findAllByOrderByTotalScoreDesc();
		model.addAttribute("scores", scores);
		return "student_score"; // 指向 templates/student_score.html
	}
	
	@GetMapping("/add")
	@ResponseBody
	public ResponseEntity<String> add() {
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
		
		return ResponseEntity.ok(output.toString());
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<String> get(@PathVariable("id") Integer id) {
		Optional<StudentScore> studentScoreOpt = studentScoreRepository.findById(id);
		if(studentScoreOpt.isPresent()) {
			return ResponseEntity.ok(studentScoreOpt.get().toString());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("無此學生資料");
		}
	}
	
	@Transactional
	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseEntity<String> update(@PathVariable("id") Integer id, StudentScore uptStudentScore) {
		Optional<StudentScore> studentScoreOpt = studentScoreRepository.findById(id);
		if(studentScoreOpt.isPresent()) {
			StudentScore studentScore = studentScoreOpt.get();
			// JPA 僅提供一個一個屬性配置
//			studentScore.setName(uptStudentScore.getName());
//			studentScore.setChineseScore(uptStudentScore.getChineseScore());
//			studentScore.setEnglishScore(uptStudentScore.getEnglishScore());
//			studentScore.setMathScore(uptStudentScore.getMathScore());
			
			// 利用 Spring Framework 所提供的 BeanUtils 來進行屬性複製
			// uptStudentScore 的資料複製到 studentScore, 但是 "id", "totalScore", "averageScore" 不需要複製
			BeanUtils.copyProperties(uptStudentScore, studentScore, "id", "totalScore", "averageScore");
			studentScore.updateTotalAndAverage();
			return ResponseEntity.ok("Update OK");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("無此學生資料");
		}
	}
	
	@PutMapping("/update/name/{id}")
	@ResponseBody
	public ResponseEntity<String> updateName(@PathVariable("id") Integer id, @RequestParam("name") String name) {
		studentScoreRepository.updateNameById(id, name);
		return ResponseEntity.ok("Update OK");
	}
	
	@Transactional
	@PutMapping("/update/{subject}/{id}")
	@ResponseBody
	public ResponseEntity<String> updateScore(@PathVariable("subject") String subject, 
							  @PathVariable("id") Integer id, 
							  @RequestParam("score") Integer score) {
		
		Optional<StudentScore> studentScoreOpt = studentScoreRepository.findById(id);
		if(studentScoreOpt.isPresent()) {
			StudentScore studentScore = studentScoreOpt.get();
			switch (subject) {
				case "chinese":
					studentScore.setChineseScore(score);
					break;
				case "english":
					studentScore.setEnglishScore(score);
					break;
				case "math":
					studentScore.setMathScore(score);
					break;
				default:
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("無此科目");
			}
			studentScore.updateTotalAndAverage();
			//studentScoreRepository.saveAndFlush(studentScore); // 若該方法有加上 @Transactional 則此行可以不用寫
			return ResponseEntity.ok("Update OK");
		} else {
			//return ResponseEntity.notFound().build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("無此學生資料");
		}
		
	}
}
