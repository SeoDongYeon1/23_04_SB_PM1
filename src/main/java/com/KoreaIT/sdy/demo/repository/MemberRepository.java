package com.KoreaIT.sdy.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.KoreaIT.sdy.demo.vo.Member;

@Mapper
public interface MemberRepository {

	public Member join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);

}
