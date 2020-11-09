package com.sbs.example.easytextboard.session;

public class Session {
	public int loginedMemberId;
	public int selectedBoardId;
	
	public Session() {
		loginedMemberId = 0;
		selectedBoardId = 1;
	}
	
	public boolean isBoarded() {
		return selectedBoardId < 1;
	}
	
	public boolean isLogined() {
		return loginedMemberId != 0;
	}
	public boolean isLogout() {
		return !isLogined();
	}
}
