package com.sbs.example.easytextboard.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.easytextboard.dto.Member;

public class MemberDao {
	List<Member> members;
	int lastId;
	

	public MemberDao() {
		lastId = 0;
	
		members = new ArrayList<Member>();

		for (int i = 1; i < 3; i++) {
			add(i+"",i+"",i+"");

		}

	}
	
	public Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}
	
	

	public boolean isExistsLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return true;
			}
		}

		return false;
	}

	public int add(String id, String password, String name) {

		Member member = new Member();
		member.id = lastId + 1;
		member.loginId = id;
		member.loginPw = password;
		member.name = name;

		members.add(member);
		lastId = member.id;
		return member.id;
	}

	public boolean isJoinableLoginId(String id) {

		for (Member member : members) {
			if (member.loginId.equals(id)) {
				return false;
			}
		}

		return true;
	}
	public int getLastId() {
		
		return lastId;
	}
	
	public int getMembersSize() {		
		return members.size();
	}
	
	public List<Member> getMembers() {
		return members;
	}
	public Member getMemberIndex(int input) {
		return members.get(input);
	}

}
