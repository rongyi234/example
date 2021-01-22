package com.rong.example.advice;

import com.rong.example.constant.IactivityConstants;
import com.rong.example.expection.BusinessRuntimeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@ControllerAdvice
public class GlobalExceptionHandler{

	private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	private  HttpServletResponse response;


	/**
	 * 必传参数校验异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseBody
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("异常：{}", e);
		response.addHeader("err-code",String.valueOf(IactivityConstants.REQUEST_ERROR_CODE));

		//网关判断http的状态值大于400，才会认为业务处理异常
		response.setStatus(406);
		return e.getMessage();
	}

	/**
	 * 业务异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = { BusinessRuntimeException.class })
	@ResponseBody
	public String handleBusinessException(BusinessRuntimeException e) {
		log.error("异常：{}", e);
		if(e.getCauseKey()!=0){
			if(e.getPatternStr()!=null){
				response.addHeader("err-pat",base64Encode(e.getPatternStr()));
			}
			response.addHeader("err-code",e.getCauseKey()+"");

		}else{
			//"24"代表个人中心组件
			response.addHeader("err-code",String.valueOf(IactivityConstants.SYSTEM_ERROR_CODE));
		}

		//网关判断http的状态值大于400，才会认为业务处理异常
		response.setStatus(406);
		return e.getMessage();
	}




	/**
	 * 基类异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public String handleException(Exception e,HttpServletResponse response) {
		log.error("异常：{}", e);
		response.addHeader("err-code",String.valueOf(IactivityConstants.SYSTEM_ERROR_CODE));

		//网关判断http的状态值大于400，才会认为业务处理异常
		response.setStatus(406);
		return e.getMessage();
	}

	/**
	 * 64位加密
	 * @param message
	 * @return
	 */
	private String base64Encode(String message){
		byte[] bytes = message.getBytes();
		//Base64 加密
		return Base64.getEncoder().encodeToString(bytes);
	}

}
