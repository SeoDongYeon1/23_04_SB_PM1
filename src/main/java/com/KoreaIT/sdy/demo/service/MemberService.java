package com.KoreaIT.sdy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.sdy.demo.repository.MemberRepository;
import com.KoreaIT.sdy.demo.util.Ut;
import com.KoreaIT.sdy.demo.vo.Member;
import com.KoreaIT.sdy.demo.vo.ResultData;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;

	public ResultData<Integer> join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		// 아이디 중복체크
		Member member = memberRepository.getMemberByLoginId(loginId);
		
		if(member!=null) {
			return ResultData.from("F-7", Ut.f("(%s)는 이미 사용중인 아이디입니다.", loginId));
		}
		
		// 이름과 이메일 중복체크
		member = memberRepository.getMemberByNameAndEmail(name, email);
		if(member != null) {
			return ResultData.from("F-8", Ut.f("(%s)이름과 (%s)이메일은 이미 사용중인 아이디입니다.", name, email));
		}
		
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		int id = memberRepository.getLastInsertId();
		return ResultData.from("S-1", "회원가입이 완료되었습니다.",id);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
	
	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}
	
}
