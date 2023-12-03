package spring.mvc.session08.controller;

import java.util.IntSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// Base url      : http://localhost:8080/SpringMVC/mvc
// Controller url: http://localhost:8080/SpringMVC/mvc/hello
@Controller
@RequestMapping(value = "/hello")
public class HelloController {
	
	/*
	 * 1. 取得 Welcome 字串服務
	 * 網址: http://localhost:8080/SpringMVC/mvc/hello/welcome
	 * */
	@GetMapping(value = "/welcome")
	@ResponseBody
	public String welcome() {
		return "Welcome Spring MVC!";
	}
	
	/*
	 * 2. ? 帶參數
	 * 網址: http://localhost:8080/SpringMVC/mvc/hello/sayhi?name=John&age=20
	 * 網址: http://localhost:8080/SpringMVC/mvc/hello/sayhi?name=Mary
	 * 限制: name 參數是一定要有的(預設), 
	 *      age 參數是不一定要有的, 初始值 = 0
	 * */
	@GetMapping(value = "/sayhi")
	@ResponseBody
	public String sayhi(@RequestParam(value = "name", required = true) String name,
						@RequestParam(value = "age", required = false, defaultValue = "0") Integer age) {
		
		String message = String.format("Hi %s, %d", name, age);
		return message;
	}
	
	/*
	 * 3. Lab 練習
	 * 網址: http://localhost:8080/SpringMVC/mvc/hello/bmi?h=170.0&w=60.0
	 * 限制: h, w 是一定要的
	 * 執行結果: h = 170.0, w = 60.0, bmi = 20.76
	 * 請設計~
	 * */
	@GetMapping(value = "/bmi")
	@ResponseBody
	public String bmi(@RequestParam(value = "h") Double h, @RequestParam(value = "w") Double w) {
		double bmi = w / Math.pow(h/100, 2);
		String message = String.format("h = %.1f, w = %.1f, bmi = %.2f", h, w, bmi);
		return message;
	}
	
	/*
	 * 4. 同名多筆資料參數
	 * 網址: http://localhost:8080/SpringMVC/mvc/hello/age?age=17&age=19&age=25
	 * 計算平均年齡
	 * */
	@GetMapping(value = "/age")
	@ResponseBody
	public String getAvgOfAge(@RequestParam("age") List<Integer> ages) {
		double avgAge = ages.stream()
							.mapToInt(Integer::intValue) // 將 Integer 轉 int
							.average() // 取得平均物件資料
							.getAsDouble(); // 取得平均的 double 值
		return String.format("avgAge = %.1f", avgAge);
	}
	
	/*
	 * 5. Lab 練習: 得到多筆 score 資料
	 * 網址：http://localhost:8080/SpringMVC/mvc/hello/exam?score=80&score=100&score=50
	 * 請自行設計一個方法，此方法可以印出最高分、最低分、平均與總分 (支援中文字印出)
	 * */
	
	@GetMapping(value = "/exam", produces = {"text/plain;charset=utf-8"})
	@ResponseBody
	public String getExamInfo(@RequestParam("score") List<Integer> scores) {
		IntSummaryStatistics stat = scores.stream()
										  .mapToInt(Integer::intValue)
										  .summaryStatistics();
		return String.format("最高分: %d 最低分: %d 平均: %.1f, 總分: %d", 
				stat.getMax(), stat.getMin(), stat.getAverage(), stat.getSum());
	}
	
	
}
