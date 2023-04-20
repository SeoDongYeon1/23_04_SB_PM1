package com.KoreaIT.sdy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.sdy.demo.repository.MemberRepository;
import com.KoreaIT.sdy.demo.vo.Member;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;

	public Member join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		return memberRepository.join(loginId, loginPw, name, nickname, cellphoneNum, email);
	}
	
}
