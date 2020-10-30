package com.sbs.example.easytextboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dto.Member;
import com.sbs.example.easytextboard.session.Session;

public class MemberController {
	List<Member> members;
	int memberid;
	int login;

	public MemberController() {
		memberid = 0;
		login = 0;
		members = new ArrayList<Member>();

		for (int i = 0; i < 3; i++) {
			add("user" + (i + 1), "user" + (i + 1), "유저" + (i + 1));

		}

	}

	private Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.id.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	private boolean isExistsLoginId(String loginId) {
		for (Member member : members) {
			if (member.id.equals(loginId)) {
				return true;
			}
		}

		return false;
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
				} else if (isJoinableLoginId(id) == false) {
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

		} else if (com.equals("member login")) {
			System.out.println("== 로그인 ==");

			if (Container.session.isLogined()) {
				System.out.println("이미 로그인 되었습니다.");
				return;
			}

			String loginId = "";
			String loginPw;

			int loginIdMaxCount = 3;
			int loginIdCount = 0;
			boolean loginIdIsValid = false;

			Member member = null;

			while (true) {
				if (loginIdMaxCount <= loginIdCount) {
					System.out.println("로그인을 취소합니다.");
					break;
				}

				System.out.printf("로그인아이디 : ");
				loginId = scan.nextLine().trim();

				if (loginId.length() == 0) {
					loginIdCount++;
					continue;
				}

				member = getMemberByLoginId(loginId);

				if (member == null) {
					loginIdCount++;
					System.out.printf("존재하지 않는 로그인아이디 입니다.\n", loginId);
					continue;
				}

				loginIdIsValid = true;
				break;
			}

			if (loginIdIsValid == false) {
				return;
			}

			int loginPwMaxCount = 3;
			int loginPwCount = 0;
			boolean loginPwIsValid = false;

			while (true) {
				if (loginPwMaxCount <= loginPwCount) {
					System.out.println("로그인을 취소합니다.");
					break;
				}

				System.out.printf("로그인비번 : ");
				loginPw = scan.nextLine().trim();

				if (loginPw.length() == 0) {
					continue;
				}

				if (member.password.equals(loginPw) == false) {
					loginPwCount++;
					System.out.printf("비밀번호가 일치하지 않습니다.\n");
					continue;
				}

				loginPwIsValid = true;

				break;
			}

			if (loginPwIsValid == false) {
				return;
			}
			System.out.printf("로그인 되었습니다. %s님 환영합니다.\n", member.name);

			Container.session.loginedMemberId = member.num;

		} else if (com.equals("member logout")) {
			if (Container.session.isLogined() == true) {
				Member member = new Member();
				member.num = Container.session.loginedMemberId;

				for (int i = 0; i < members.size(); i++) {
					if (member.num == members.get(i).num) {
						System.out.printf("%s님이 로그아웃 하였습니다.\n", members.get(i).name);
						Container.session.loginedMemberId = 0;
					}
				}
			} else
				System.out.println("로그인 후 이용해주세요.");

		} else if (com.equals("member whoami")) {
			if (Container.session.isLogined() == true) {
				Member member = new Member();
				member.num = Container.session.loginedMemberId;

			} else
				System.out.println("당신은 나그네입니다.");

		} else if (com.equals("member list")) {
			for (int i = 0; i < members.size(); i++) {
				System.out.println(members.get(i).num);
				System.out.println(members.get(i).id);
				System.out.println(members.get(i).password);
				System.out.println(members.get(i).name);
				System.out.println("---------------");
			}
		}

	}

}
