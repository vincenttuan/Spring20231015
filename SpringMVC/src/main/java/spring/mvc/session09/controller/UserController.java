package spring.mvc.session09.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session09.dao.DataDao;
import spring.mvc.session09.dao.UserDao;
import spring.mvc.session09.entity.EducationData;
import spring.mvc.session09.entity.InterestData;
import spring.mvc.session09.entity.SexData;
import spring.mvc.session09.entity.User;

@Controller
@RequestMapping("/session09/user")
public class UserController {
	
	@Autowired
	@Qualifier("userDaoImplInMemory")
	private UserDao userDao;
	
	@Autowired
	@Qualifier("dataDaoImplInMemory")
	private DataDao dataDao;
	
	// 首頁
	@GetMapping("/")
	public String index(@ModelAttribute User user, Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("buttonName", "新增");
		model.addAttribute("users", userDao.findAllUsers());
		model.addAttribute("educations", dataDao.findAllEducationDatas());
		model.addAttribute("interests", dataDao.findAllInterestDatas());
		model.addAttribute("sexs", dataDao.findAllSexDatas());
		return "session09/user";
	}
	
	@PostMapping(value = "/", produces = {"text/plain;charset=utf-8"})
	//@ResponseBody
	public String add(@Valid User user, BindingResult result, Model model) {
		if(result.hasErrors()) { // 若有錯誤發生
			model.addAttribute("user", user);
			//-------------------------------------------------------------
			model.addAttribute("_method", "POST");
			model.addAttribute("buttonName", "新增");
			model.addAttribute("users", userDao.findAllUsers());
			model.addAttribute("educations", dataDao.findAllEducationDatas());
			model.addAttribute("interests", dataDao.findAllInterestDatas());
			model.addAttribute("sexs", dataDao.findAllSexDatas());
			return "session09/user";
		}
		// 根據 id 找到符合的物件
		Optional<EducationData> eduOpt = dataDao.getEducationDataById(user.getEducationId());
		Optional<SexData> sexOpt = dataDao.getSexDataById(user.getSexId());
		List<InterestData> interestDatas = dataDao.findInterestDatasByIds(user.getInterestIds());
		// 注入
		if(eduOpt.isPresent()) {
			user.setEducation(eduOpt.get());
		}
		
		if(sexOpt.isPresent()) {
			user.setSex(sexOpt.get());
		}
		
		user.setInterests(interestDatas);
		
		//return user.toString();
		
		// 新增
		userDao.addUser(user);
		return "redirect:/mvc/session09/user/"; // 重導到首頁
		
	}
	
	
}
