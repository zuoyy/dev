package com.zuoyy.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.Date;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class JsonResponse {
	
	private Integer code;
	private String msg;
	private Date timestamp = new Date();
	private Object data;

	/**
	 * 构造器
	 */
	public JsonResponse() {
		this.code = HttpStatus.OK.value();
	}
	
	public JsonResponse(int code) {
		this.code = code;
	}
	
	public JsonResponse(int code, Object data) {
		this.code = code;
		this.data = data;
	}
	
	public JsonResponse(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}


	/**
	 * 成功
	 * @return
	 */
	public static JsonResponse success() {
		return new JsonResponse(HttpStatus.OK.value());
	}

	public static JsonResponse success(String msg) {
		return new JsonResponse(HttpStatus.OK.value(),msg);
	}

	public static JsonResponse success(Object data) {
		return new JsonResponse(HttpStatus.OK.value(), data);
	}


	
	/**
	 * 错误
	 * @return
	 */
	public static JsonResponse error(int code,String msg) {
		return new JsonResponse(code, msg);
	}

	public static JsonResponse error(String msg) {
		return new JsonResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
	}
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
}
