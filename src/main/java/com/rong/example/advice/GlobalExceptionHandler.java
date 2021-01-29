package com.rong.example.advice;

import com.rong.example.constant.ErrorCodeEnum;
import com.rong.example.constant.ExampleConstants;
import com.rong.example.expection.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
	public void handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		StringBuilder sb=new StringBuilder();
		for (ObjectError error : e.getBindingResult().getAllErrors()) {
			sb.append("[").append(error.getDefaultMessage()).append("]");
		}
		log.error("异常 ："+ sb.toString());
		response.addHeader(ExampleConstants.HTTP_FLAG_CODE,ErrorCodeEnum.REQUEST_PARAMS_ERROR.getCode().toString());
		response.addHeader(ExampleConstants.HTTP_FLAG_MSG,sb.toString());
	}

	/**
	 * 业务异常处理
	 *
	 */
	@ExceptionHandler(value = { ServiceException.class })
	@ResponseBody
	public void handleBusinessException(ServiceException e) {
		log.error("异常 ："+ ErrorCodeEnum.getMsgByCode(e.getCode()));
		response.addHeader(ExampleConstants.HTTP_FLAG_CODE,String.valueOf(e.getCode()));

	}




	/**
	 * 基类异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public void handleException(Exception e) {
		log.error("异常 ："+ e.getMessage());
		response.addHeader(ExampleConstants.HTTP_FLAG_CODE,String.valueOf(ErrorCodeEnum.SYSTEM_ERROR.getCode()));

		//response.setStatus(406);
	}


}
