package com.sbs.example.easytextboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dto.Article;
import com.sbs.example.easytextboard.dto.Member;

public class ArticleController extends Controller {
	public void run(Scanner scan, String com) {

		if (com.equals("article add")) {

			if (Container.session.isLogout()) {
				System.out.println("로그인 후 이용해주세요.");
				return;
			}
			System.out.printf("제목: ");
			String title = scan.nextLine();

			System.out.printf("내용: ");
			String body = scan.nextLine();
			int num = Container.session.loginedMemberId;

			for (Member member : Container.memberservice.getmembers()) {
				if (member.num == num) {
					Container.articleservice.add(title, body, member.id);
					System.out.println(num + "님이 " + Container.articleservice.getlastid() + "번째 게시물을 생성하였습니다.");
				}

			}

		} else if (com.startsWith("article list ")) {

			int page = Integer.parseInt(com.split(" ")[2]);
			if (Container.articleservice.getarticlesize() == 0) {
				System.out.println("게시글이 존재하지 않습니다.");
				return;
			} else {

				System.out.println("== 게시물 리스트 ==");
				Container.articleservice.List(page);

			}
		} else if (com.startsWith("article detail ")) {
			int inputid = Integer.parseInt(com.split(" ")[2]);
			if (Container.articleservice.getarticlesize() == 0 || Container.articleservice.getarticlesize() < inputid) {
				System.out.println("게시글이 존재하지 않습니다.");
				return;
			} else if (inputid == Container.articleservice.getarticleIndex(inputid - 1).id) {

				System.out.printf("번호: %d\n", Container.articleservice.getarticleIndex(inputid - 1).id);
				System.out.printf("제목: %s\n", Container.articleservice.getarticleIndex(inputid - 1).title);
				System.out.printf("내용: %s\n", Container.articleservice.getarticleIndex(inputid - 1).body);
				System.out.printf("날짜: %s\n", Container.articleservice.getarticleIndex(inputid - 1).regDate);
				System.out.printf("작성자: %s\n", Container.articleservice.getarticleIndex(inputid - 1).memberId);
			}

		} else if (com.startsWith("article delete ")) {
			if (Container.session.isLogout()) {
				System.out.println("로그인 후 이용해주세요.");
				return;
			}
			int inputid = Integer.parseInt(com.split(" ")[2]);
			if (Container.articleservice.getarticlesize() == 0 || Container.articleservice.getarticlesize() < inputid) {
				System.out.println("게시글이 존재하지 않습니다.");
				return;
			} else {
				int num = Container.session.loginedMemberId;

				for (Member member : Container.memberservice.getmembers()) {
					if (member.num == num) {
						if (member.id.equals(Container.articleservice.getarticleIndex(inputid - 1).memberId)) {
							Container.articleservice.delete(inputid);
							return;
						}
					}
				}
				System.out.println("작성자만 삭제할 수 있습니다.");

			}

		} else if (com.startsWith("article modify ")) {
			if (Container.session.isLogout()) {
				System.out.println("로그인 후 이용해주세요.");
				return;
			}
			int inputid = Integer.parseInt(com.split(" ")[2]);
			if (Container.articleservice.getarticlesize() == 0 || Container.articleservice.getarticlesize() < inputid) {
				System.out.println("게시글이 존재하지 않습니다.");
				return;
			} else {
				
				int num = Container.session.loginedMemberId;
				for (Member member : Container.memberservice.getmembers()) {
					if (member.num == num) {
						if (member.id.equals(Container.articleservice.getarticleIndex(inputid - 1).memberId)) {
							Container.articleservice.modify(inputid);
							return;
						}
					}
				}
				System.out.println("작성자만 수정할 수 있습니다.");
			}

			

		} else if (com.startsWith("article search ")) {
			String[] commandBit = com.split(" ");

			String inputbody = commandBit[2];

			if (Container.articleservice.getarticlesize() <= 0) {
				System.out.println("페이지가 존재하지 않습니다.");
				return;
			} else if (commandBit.length > 3) {
				int page = 1;
				page = Integer.parseInt(commandBit[3]);
				Container.articleservice.search(inputbody, page);
			} else {
				System.out.printf("== %s 가 포함된 게시물 ==\n", inputbody);
				List<Article> listarticle = new ArrayList<Article>();

				for (int i = 0; i <= Container.articleservice.getarticlesize() - 1; i++) {
					if (Container.articleservice.getarticleIndex(i).body.contains(inputbody)) {
						listarticle.add(Container.articleservice.getarticleIndex(i));
					}
				}
				if (listarticle.size() <= 0) {
					System.out.printf("%s가 포함된 게시물이 존재하지 않습니다.\n", inputbody);
					return;
				}
				for (int i = listarticle.size() - 1; i >= 0; i--) {
					System.out.printf("%d / %s\n", listarticle.get(i).id, listarticle.get(i).title);
				}
			}

		}
	}
}
