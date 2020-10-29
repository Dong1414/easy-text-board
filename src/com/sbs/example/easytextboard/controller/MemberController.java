package com.sbs.example.easytextboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import com.sbs.example.easytextboard.dto.Member;

public class MemberController {
	List<Member> members;
	int memberid;
	int login;

	public MemberController() {
		memberid = 0;
		login = 0;
		members = new ArrayList<Member>();
	}

	private int add(String id, String password, String name) {

		Member member = new Member();
		member.num = memberid + 1;
		member.id = id;
		member.password = password;
		member.name = name;

		members.add(member);
		memberid = member.num;
		return member.num;
	}

	private boolean isJoinableLoginId(String id) {

		for (Member member : members) {
			if (member.id.equals(id)) {
				return false;
			}
		}

		return true;
	}

	private void Login(String id, String password) {

		for (int i = 0; i < members.size(); i++) {
			if (id.equals(members.get(i).id)) {
				if (members.get(i).password.equals(password)) {
					System.out.printf("%s님 환영합니다.\n", members.get(i).name);
					login = i + 1;
					return;
				} else {
					System.out.println("비밀번호가 일치하지 않습니다.");
					return;
				}
			}
			if (i == members.size() - 1) {
				System.out.println("아이디가 존재하지 않습니다.");
			}
		}
	}

	public void run(Scanner scan, String com) {
		if (com.equals("member join")) {
			int loginMax = 3;
			int loginCount = 0;
			boolean loginValid = false;
			String id = "";
			String password = "";
			String name = "";
			while (true) {
				if (loginCount >= loginMax) {
					System.out.println("다음에 다시 시도해주세요");
					break;
				}
				
				System.out.printf("로그인 아이디: ");
				id = scan.nextLine().trim();
				
				if (id.length() == 0) {				
					loginCount++;
					continue;
				}				
				else if (isJoinableLoginId(id) == false) {
					System.out.printf("%s(은)는 이미 사용중인 아이디 입니다.\n", id);
					loginCount++;
					continue;
				}
				loginValid = true;
				break;
			}
			if (loginValid == false) {
				return;
			}
			while (true) {
				System.out.printf("비밀번호: ");
				password = scan.nextLine().trim();
				if (password.length() == 0) {
					continue;
				}

				break;
			}
			while (true) {
				System.out.printf("이름: ");
				name = scan.nextLine().trim();
				if (name.length() == 0) {
					continue;
				}

				break;
			}
				add(id, password, name);

				System.out.println(memberid + "번째 아이디 생성되었습니다.");
		
			}
		 else if (com.equals("member login")) {
			if (members.size() == 0) {
				System.out.println("현재 존재하는 계정이 없습니다.");
				return;
			}
			System.out.printf("로그인 아이디: ");
			String id = scan.nextLine().trim();

			System.out.printf("비밀번호: ");
			String password = scan.nextLine().trim();

			Login(id, password);

		} else if (com.equals("member logout")) {
			if (login > 0) {
				System.out.printf("%s님이 로그아웃 하였습니다.\n", members.get(login - 1).name);
				login = 0;
			} else
				System.out.println("현재 사용자가 없습니다.");

		} else if (com.equals("member whoami")) {
			if (login > 0) {
				System.out.printf("현재 사용자 : %s / %s\n", members.get(login - 1).id, members.get(login - 1).name);
				login = 0;
			} else
				System.out.println("현재 사용자가 없습니다.");

		}

	}

}
