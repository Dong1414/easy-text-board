package com.sbs.example.easytextboard.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.easytextboard.dto.Member;

public class MemberDao {
	List<Member> members;
	int memberid;
	

	public MemberDao() {
		memberid = 0;
	
		members = new ArrayList<Member>();

		for (int i = 0; i < 3; i++) {
			add("user" + (i + 1), "user" + (i + 1), "유저" + (i + 1));

		}

	}
	
	public Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.id.equals(loginId)) {
				return member;
			}
		}
		return null;
	}
	
	

	public boolean isExistsLoginId(String loginId) {
		for (Member member : members) {
			if (member.id.equals(loginId)) {
				return true;
			}
		}

		return false;
	}

	public int add(String id, String password, String name) {

		Member member = new Member();
		member.num = memberid + 1;
		member.id = id;
		member.password = password;
		member.name = name;

		members.add(member);
		memberid = member.num;
		return member.num;
	}

	public boolean isJoinableLoginId(String id) {

		for (Member member : members) {
			if (member.id.equals(id)) {
				return false;
			}
		}

		return true;
	}
	public int getmemberid() {
		
		return memberid;
	}
	
	public int getmembersSize() {		
		return members.size();
	}
	
	public List<Member> getmembers() {
		return members;
	}
	public Member getmemberIndex(int input) {
		return members.get(input);
	}

}
