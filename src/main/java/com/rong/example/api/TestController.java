package com.rong.example.api;


import com.rong.example.advice.SessionContextHolder;
import com.rong.example.bean.bo.SessionContext;
import com.rong.example.kafka.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/test")
public class TestController {
	private static final Logger log = LoggerFactory.getLogger(TestController.class);

	@Resource(name = "commonThreadPool")
	private ExecutorService commonThreadPool;

	@Autowired
	private Producer producer;


	/**
	 *   测试kafka
	 */
	@RequestMapping("/kafka")
	public String testJobInitKafkaListener(){
		String result="success";
		try {
			producer.send();
		} catch (Exception e) {
			result="Exception = "+e.toString();
		}
		return result;
	}

	/**
	 *   测试kafka
	 */
	@RequestMapping("/kafkaHead")
	public String testKafkaHead(){
		String result="success";
		try {
			producer.sendWithHead();
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

		createAsyncThread();

		return "example project run successfully";
	}


	/**
	 * 测试个异步线程
	 */
	public void createAsyncThread() {
		SessionContext context= SessionContextHolder.getContext();
		try {
			commonThreadPool.execute(()->{
				try {
					SessionContextHolder.setContext(context);
					log.info("看，我重启了一个线程，并设置了上下文: "+SessionContextHolder.getContext());
				} catch (Exception e) {
					log.error("异步线程异常了："+e.toString());
				}
			});
		} catch (RejectedExecutionException e) {
			log.error("线程池溢出，创建线程失败");

		}
	}


}
