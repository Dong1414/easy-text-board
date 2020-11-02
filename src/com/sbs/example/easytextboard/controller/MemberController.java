package com.sbs.example.easytextboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dto.Member;
import com.sbs.example.easytextboard.session.Session;

public class MemberController extends Controller {

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
				} else if (Container.memberservice.isJoinableLoginId(id) == false) {
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
			Container.memberservice.add(id, password, name);

			System.out.println( Container.memberservice.getmemberid() + "번째 아이디 생성되었습니다.");

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

				member = Container.memberservice.getMemberByLoginId(loginId);

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
			if (Container.session.isLogout()) {
				System.out.println("로그인 후 이용해주세요.");
			} else {
				Member member = new Member();
				member.num = Container.session.loginedMemberId;

				for (int i = 0; i < Container.memberservice.getmembersSize() ; i++) {
					if (member.num == Container.memberservice.getmemberIndex(i).num) {
						System.out.printf("%s님이 로그아웃 하였습니다.\n", Container.memberservice.getmemberIndex(i).name);
						Container.session.loginedMemberId = 0;
					}
				}

			}

		} else if (com.equals("member whoami")) {
			if (Container.session.isLogout()) {
				System.out.println("당신은 나그네 입니다.");
				return;
			}

			int loginedMemberId = Container.session.loginedMemberId;
			System.out.printf("당신의 회원번호는 %d번 입니다.\n", loginedMemberId);

		} else if (com.equals("member modify")) {
			if (Container.session.isLogout()) {
				System.out.println("로그인 후 이용해주세요.");
				return;
			}
			int num = Container.session.loginedMemberId;
			Member member = new Member();
			System.out.printf("이름 : ");
			String name = scan.nextLine();
			member.id = Container.memberservice.getmemberIndex(num-1).id;
			member.password = Container.memberservice.getmemberIndex(num-1).password;
			member.num = Container.memberservice.getmemberIndex(num-1).num;
			member.name = name;
			Container.memberservice.getmembers().set(num-1, member);  
			System.out.println(Container.memberservice.getmemberIndex(num-1).id + "님의 이름이 " + Container.memberservice.getmemberIndex(num-1).name + "으로 수정되었습니다.");

		}
	}

	


}
