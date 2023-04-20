package com.KoreaIT.sdy.demo.vo;

import lombok.Getter;

public class ResultData<DT> {
	@Getter
	private String ResultCode;
	@Getter
	private String msg;
	@Getter
	private DT data1;
	
	public static <DT> ResultData<DT> from(String ResultCode, String msg) {
		return from(ResultCode, msg, null);
	}
	
	public static <DT> ResultData<DT> from(String ResultCode, String msg, DT data1) {
		ResultData<DT> rd = new ResultData<DT>();
		
		rd.ResultCode = ResultCode;
		rd.msg = msg;
		rd.data1 = data1;
		
		return rd;
	}
	
	public boolean isSuccess() {
		return ResultCode.startsWith("S-1");
	}
	
	public boolean isFail() {
		return isSuccess()==false;
	}

	public static <DT> ResultData<DT> newData(ResultData<?> Rd, DT newData) {
		return from(Rd.getResultCode(), Rd.getMsg(), newData);
	}

}
