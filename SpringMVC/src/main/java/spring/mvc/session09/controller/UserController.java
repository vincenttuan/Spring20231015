package spring.mvc.session09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session09.dao.DataDao;
import spring.mvc.session09.dao.UserDao;
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
	
	@PostMapping("/")
	@ResponseBody
	public String add(@ModelAttribute User user) {
		return user.toString();
	}
	
}
