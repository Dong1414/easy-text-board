package com.sbs.example.easytextboard.service;

import java.util.List;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dao.MemberDao;
import com.sbs.example.easytextboard.dto.Member;

public class MemberService {
	MemberDao memberdao = Container.memberdao;
	
	public boolean isJoinableLoginId(String id) {

		return memberdao.isJoinableLoginId(id);
	}

	public void add(String id, String password, String name) {
		memberdao.add(id, password, name);
		
	}

	public Member getMemberByLoginId(String loginId) {
		
		return memberdao.getMemberByLoginId(loginId);
	}

	public int getmembersSize() {
		
		return memberdao.getmembersSize();
	}

	public Member getmemberIndex(int i) {
		
		return memberdao.getmemberIndex(i);
	}

	public List<Member> getmembers() {

		return memberdao.getmembers();
	}

	public int getmemberid() {		
		return memberdao.getmemberid();
	}
	

}
