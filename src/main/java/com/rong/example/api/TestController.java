package com.rong.example.api;


import com.rong.example.advice.session.SessionContextHolder;
import com.rong.example.advice.session.SessionContext;
import com.rong.example.kafka.producer.Producer;
import com.rong.example.propLoad.database.DictionaryCacheService;
import com.rong.example.propLoad.properties.LoadProperties;
import com.rong.example.propLoad.yml.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
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

	@Autowired
	private Person person;



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
					//放在首行（先设置线程名称）
					SessionContextHolder.setContext(context);
					log.info(">> 开启异步线程：");


					log.info("看，我设置了上下文: "+SessionContextHolder.getContext());

				} catch (Exception e) {
					log.error("异步线程异常了："+e.toString());
				}finally {
					log.info("<< 异步线程结束");
				}
			});
		} catch (RejectedExecutionException e) {
			log.error("线程池溢出，创建线程失败");

		}
	}


	/**
	 *   测试yml配置
	 */
	@RequestMapping("/yml")
	public String testYml(){

		return person.toString();
	}

	/**
	 *   测试loadProperties
	 */
	@RequestMapping("/loadProperties")
	public String loadProperties(){

		return LoadProperties.paramMap.toString();
	}

	/**
	 *   测试jdbc预加载数据库
	 */
	@RequestMapping("/loadDB")
	public String loadDB(){

		for(Map.Entry<String,String> item: DictionaryCacheService.dictionaryInfoMap.entrySet()){
			System.out.println("加载配置：key="+item.getKey()+", value="+item.getValue());
		}
		return "success";
	}

}
