package com.KoreaIT.sdy.demo.controller;

import javax.servlet.http.HttpSession;

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
			return ResultData.from("F-2", "비밀번호를 입력해주세요.");
		}
		if(Ut.empty(name)) {
			return ResultData.from("F-3", "이름을 입력해주세요.");
		}
		if(Ut.empty(nickname)) {
			return ResultData.from("F-4", "닉네임을 입력해주세요.");
		}
		if(Ut.empty(cellphoneNum)) {
			return ResultData.from("F-5", "전화번호를 입력해주세요.");
		}
		if(Ut.empty(email)) {
			return ResultData.from("F-6", "이메일을 입력해주세요.");
		}
		
		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		if (joinRd.isFail()) {
			return joinRd;
		}
		
		Member member = memberService.getMemberById(joinRd.getData1());
		
		return ResultData.newData(joinRd, member);
	}
	
	@RequestMapping("/usr/member/login")
	public String login(HttpSession httpSession, String loginId, String loginPw) {
		
		return "usr/member/login";
	}
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData<Member> doLogin(HttpSession httpSession, String loginId, String loginPw) {
		boolean isLogined = false;
		
		if(httpSession.getAttribute("loginedMemberId")!=null) {
			isLogined = true;
		}
		
		if(isLogined) {
			return ResultData.from("F-A", "이미 로그인 상태입니다.");
		}
		
		if(Ut.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요.");
		}
		if(Ut.empty(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력해주세요.");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member==null) {
			return ResultData.from("F-3", "아이디 또는 비밀번호를 확인해주세요.");
		}
		
		if(member.getLoginPw().equals(loginPw)==false) {
			return ResultData.from("F-4", "아이디 또는 비밀번호를 확인해주세요.");
		}
		httpSession.setAttribute("loginedMemberId", member.getId());
		return ResultData.from("S-1", Ut.f("%s님 반갑습니다.", member.getNickname()), member);
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpSession httpSession) {
		boolean isLogined = false;
		
		if(httpSession.getAttribute("loginedMemberId")!=null) {
			isLogined = true;
		}
		
		if(isLogined==false) {
			return Ut.jsHistoryBack("F-A", "로그인 상태가 아닙니다.");
		}
		httpSession.removeAttribute("loginedMemberId");
		return Ut.jsReplace("로그아웃 되었습니다.","../home/main");
	}
	
}
