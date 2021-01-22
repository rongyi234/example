package com.rong.example.api;


import com.rong.example.service.UserMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
	private static final Logger log = LoggerFactory.getLogger(TestController.class);




	/**
	 *   测试初始化kafka
	 */
	@RequestMapping("/testJobInitKafkaListener")
	public String testJobInitKafkaListener(@RequestParam Integer cltId){

		String result="success";
		try {

		} catch (Exception e) {
			result="Exception = "+e.toString();
		}

		return result;
	}

	/**
	 *   测试项目启用状态
	 * @param
	 * @return
	 */
	@RequestMapping("/testProject")
	public String testProject(){
		return "example project run successfully";
	}



}
