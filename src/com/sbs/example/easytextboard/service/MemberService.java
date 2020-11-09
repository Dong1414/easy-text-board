package com.sbs.example.easytextboard.service;

import java.util.List;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dao.MemberDao;
import com.sbs.example.easytextboard.dto.Member;

public class MemberService {
	MemberDao memberdao = Container.memberDao;
	
	public boolean isJoinableLoginId(String id) {

		return memberdao.isJoinableLoginId(id);
	}

	public void add(String id, String password, String name) {
		memberdao.add(id, password, name);
		
	}

	public Member getMemberByLoginId(String loginId) {
		
		return memberdao.getMemberByLoginId(loginId);
	}

	public int getMembersSize() {
		
		return memberdao.getMembersSize();
	}

	public Member getMemberIndex(int i) {
		
		return memberdao.getMemberIndex(i);
	}

	public List<Member> getmembers() {

		return memberdao.getMembers();
	}

	public int getmemberid() {		
		return memberdao.getLastId();
	}
	

}
