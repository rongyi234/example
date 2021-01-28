package com.rong.example.advice;

import com.rong.example.constant.ErrorCodeEnum;
import com.rong.example.constant.ExampleConstants;
import com.rong.example.expection.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler{

	private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	private  HttpServletResponse response;


	/**
	 * 注解@Valid 必传参数校验异常处理
	 *
	 */
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseBody
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("异常："+ e.getMessage());
		response.addHeader(ExampleConstants.HTTP_FLAG_CODE,String.valueOf(ErrorCodeEnum.SYSTEM_ERROR.getCode()));
		return e.getMessage();
	}

	/**
	 * 业务异常处理
	 *
	 */
	@ExceptionHandler(value = { ServiceException.class })
	@ResponseBody
	public void handleBusinessException(ServiceException e) {
		log.error("异常："+ ErrorCodeEnum.getMsgByCode(e.getCode()));
		response.addHeader(ExampleConstants.HTTP_FLAG_CODE,String.valueOf(e.getCode()));

	}




	/**
	 * 基类异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public String handleException(Exception e,HttpServletResponse response) {
		log.error("异常："+ e.getMessage());
		response.addHeader(ExampleConstants.HTTP_FLAG_CODE,String.valueOf(ErrorCodeEnum.SYSTEM_ERROR.getCode()));

		//网关判断http的状态值大于400，才会认为业务处理异常
		//response.setStatus(406);
		return e.getMessage();
	}


}
