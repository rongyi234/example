package com.rong.example.expection;


/**
 * unchecked business exception
 * @author Stephen
 */
public class BusinessRuntimeException extends RuntimeException{

	private static final long serialVersionUID = -4809687650133434131L;
	
	/**
	 * 错误代号
	 */
	private final int causeKey;
	
	private transient String patternStr;
	
	/**
	 * 失败时仍然需要传递出的参数，慎用
	 */
	private transient Object objPersist;
	
	public Object getObjPersist() {
		return objPersist;
	}

	public BusinessRuntimeException withObjPersist(Object objPersist) {
		this.objPersist = objPersist;
		return this;
	}
	
	public int getCauseKey() {
		return causeKey;
	}

	public BusinessRuntimeException(int causeKey) {
		super();
		this.causeKey = causeKey;
	}
	
	public BusinessRuntimeException(int causeKey,String msg) {
		super(msg);
		this.causeKey = causeKey;
	}

	public BusinessRuntimeException(int causeKey, Throwable cause) {
		super(cause);
		this.causeKey = causeKey;
	}
	
	public BusinessRuntimeException(int causeKey, String msg,Throwable cause) {
		super(msg,cause);
		this.causeKey = causeKey;
	}
	
	public BusinessRuntimeException withPatternStr(String patternStr) {
		this.patternStr = patternStr;
		return this;
	}
	
	public String getPatternStr() {
		return patternStr;
	}

	public void setPatternStr(String patternStr) {
		this.patternStr = patternStr;
	}

	@Override
	public String getMessage() {
		String msg = super.getMessage();
		return "[causeKey:"+causeKey+"]->"+(msg == null?"":msg);
	}
}
