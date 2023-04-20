package com.KoreaIT.sdy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.sdy.demo.service.MemberService;
import com.KoreaIT.sdy.demo.util.Ut;
import com.KoreaIT.sdy.demo.vo.Member;
import com.KoreaIT.sdy.demo.vo.ResultData;

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<?> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		if(Ut.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요.");
		}
		if(Ut.empty(loginPw)) {
			return ResultData.from("F-1", "비밀번호를 입력해주세요.");
		}
		if(Ut.empty(name)) {
			return ResultData.from("F-1", "이름을 입력해주세요.");
		}
		if(Ut.empty(nickname)) {
			return ResultData.from("F-1", "닉네임을 입력해주세요.");
		}
		if(Ut.empty(cellphoneNum)) {
			return ResultData.from("F-1", "전화번호를 입력해주세요.");
		}
		if(Ut.empty(email)) {
			return ResultData.from("F-1", "이메일을 입력해주세요.");
		}
		
		int id = memberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		Member member = memberService.getMemberById(id);
		
		return ResultData.from("S-1", "회원가입 되었습니다.", member);
	}
	
}
