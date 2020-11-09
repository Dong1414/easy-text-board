package com.sbs.example.easytextboard.controller;

import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dto.Member;
import com.sbs.example.easytextboard.service.MemberService;

public class MemberController extends Controller {
	private MemberService memberService;

	public MemberController() {
		memberService = Container.memberService;
	}

	public void doCommand(String com) {
		if (com.equals("member join")) {
			join();
		} else if (com.equals("member login")) {
			login();
		} else if (com.equals("member logout")) {
			logout();
		} else if (com.equals("member whoami")) {
			whoami();
		} else if (com.equals("member modify")) {
			modify();
		}
	}

	private void modify() {
		if (Container.session.isLogout()) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		Scanner scan = Container.scanner;
		int num = Container.session.loginedMemberId;
		Member member = new Member();
		System.out.printf("이름 : ");
		String name = scan.nextLine();
		member.loginId = memberService.getMemberIndex(num - 1).loginId;
		member.loginPw = memberService.getMemberIndex(num - 1).loginPw;
		member.id = memberService.getMemberIndex(num - 1).id;
		member.name = name;
		memberService.getmembers().set(num - 1, member);
		System.out.println(memberService.getMemberIndex(num - 1).loginId + "님의 이름이 "
				+ memberService.getMemberIndex(num - 1).name + "으로 수정되었습니다.");		
	}

	private void whoami() {
		if (Container.session.isLogout()) {
			System.out.println("당신은 나그네 입니다.");
			return;
		}

		int loginedMemberId = Container.session.loginedMemberId;
		Member member = memberService.getMemberIndex(loginedMemberId);
		System.out.println("로그인 아이디 : " + member.loginId);
		System.out.println("로그인 이름 : " + member.name);
	}

	private void logout() {
		if (Container.session.isLogout()) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		int memberId = Container.session.loginedMemberId;
		Member member = memberService.getMemberIndex(memberId);
		System.out.println(member.name + "님이 로그아웃 하였습니다.");

		Container.session.loginedMemberId = 0;

	}

	private void login() {
		System.out.println("== 로그인 ==");

		if (Container.session.isLogined()) {
			System.out.println("이미 로그인 되었습니다.");
			return;
		}
		Scanner scan = Container.scanner;
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

			member = memberService.getMemberByLoginId(loginId);

			if (member == null) {
				loginIdCount++;
				System.out.printf("존재하지 않는 로그인아이디 입니다.\n");
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

			if (member.loginPw.equals(loginPw) == false) {
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
		System.out.printf("로그인 되었습니다. %s님 환영합니다.\n", member.loginId);

		Container.session.loginedMemberId = member.id;

	}

	private void join() {
		Scanner scan = Container.scanner;
		int loginMax = 3;
		int loginCount = 0;
		boolean loginValid = false;
		String loginId = "";
		String loginPw = "";
		String name = "";
		while (true) {
			if (loginCount >= loginMax) {
				System.out.println("다음에 다시 시도해주세요");
				break;
			}

			System.out.printf("로그인 아이디: ");
			loginId = scan.nextLine().trim();

			if (loginId.length() == 0) {
				loginCount++;
				continue;
			} else if (memberService.isJoinableLoginId(loginId) == false) {
				System.out.printf("%s(은)는 이미 사용중인 아이디 입니다.\n", loginId);
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
			loginPw = scan.nextLine().trim();
			if (loginPw.length() == 0) {
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
		memberService.add(loginId, loginPw, name);

		System.out.println(memberService.getmemberid() + "번째 아이디 생성되었습니다.");

	}
}