package com.sbs.example.easytextboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dto.Article;
import com.sbs.example.easytextboard.dto.Board;
import com.sbs.example.easytextboard.dto.Member;
import com.sbs.example.easytextboard.service.ArticleService;

public class ArticleController extends Controller {
	private ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService;
	}

	public void doCommand(String cmd) {

		if (cmd.equals("article add")) {
			if (Container.session.isLogout()) {
				System.out.println("로그인 후 이용해주세요.");
				return;
			}
			add();

		} else if (cmd.equals("article makeboard")) {
			if (Container.session.isLogout()) {
				System.out.println("로그인 후 이용해주세요.");
				return;
			}
			makeBoard();

		} else if (cmd.startsWith("article selectboard ")) {
			selectBoard(cmd);

		} else if (cmd.equals("article list")) {

			if (articleService.getArticleSize() == 0) {
				System.out.println("게시글이 존재하지 않습니다.");
				return;
			} else {
				System.out.println("== 게시물 리스트 ==");
				list();
			}
		} else if (cmd.startsWith("article detail ")) {
			detail(cmd);
		} else if (cmd.startsWith("article delete ")) {
			if (Container.session.isLogout()) {
				System.out.println("로그인 후 이용해주세요.");
				return;
			}
			delete(cmd);

		} else if (cmd.startsWith("article modify ")) {
			if (Container.session.isLogout()) {
				System.out.println("로그인 후 이용해주세요.");
				return;
			}
			modify(cmd);

		} else if (cmd.startsWith("article search ")) {
			search(cmd);

		}
	}

	private void search(String cmd) {
		String[] commandBit = cmd.split(" ");

		String inputBody = commandBit[2];

		if (articleService.getArticleSize() <= 0) {
			System.out.println("페이지가 존재하지 않습니다.");
			return;
		} else if (commandBit.length > 3) {
			int page = 1;
			page = Integer.parseInt(commandBit[3]);
			articleService.search(inputBody, page);
		} else {
			System.out.printf("== %s 가 포함된 게시물 ==\n", inputBody);
			List<Article> listArticle = new ArrayList<Article>();

			for (int i = 0; i <= articleService.getArticleSize() - 1; i++) {
				if (articleService.getarticleIndex(i).body.contains(inputBody)) {
					listArticle.add(articleService.getarticleIndex(i));
				}
			}
			if (listArticle.size() <= 0) {
				System.out.printf("%s가 포함된 게시물이 존재하지 않습니다.\n", inputBody);
				return;
			}
			for (int i = listArticle.size() - 1; i >= 0; i--) {
				System.out.printf("%d / %s\n", listArticle.get(i).id, listArticle.get(i).title);
			}
		}

	}

	private void modify(String cmd) {
		int inputid = Integer.parseInt(cmd.split(" ")[2]);
		if (articleService.getArticleSize() == 0 || articleService.getArticleSize() < inputid) {
			System.out.println("게시글이 존재하지 않습니다.");
			return;
		} else {

			int num = Container.session.loginedMemberId;
			for (Member member : Container.memberService.getmembers()) {
				if (member.id == num) {
					if (member.loginId.equals(articleService.getarticleIndex(inputid - 1).memberId)) {
						articleService.modify(inputid);
						return;
					}
				}
			}
			System.out.println("작성자만 수정할 수 있습니다.");
		}

	}

	private void delete(String cmd) {
		int inputid = Integer.parseInt(cmd.split(" ")[2]);
		if (articleService.getArticleSize() == 0 || articleService.getArticleSize() < inputid) {
			System.out.println("게시글이 존재하지 않습니다.");
			return;
		} else {
			int num = Container.session.loginedMemberId;

			for (Member member : Container.memberService.getmembers()) {
				if (member.id == num) {
					if (member.loginId.equals(articleService.getarticleIndex(inputid - 1).memberId)) {
						articleService.delete(inputid);
						return;
					}
				}
			}
			System.out.println("작성자만 삭제할 수 있습니다.");

		}
	}

	private void detail(String cmd) {
		int inputid = Integer.parseInt(cmd.split(" ")[2]);
		if (articleService.getArticleSize() == 0 || articleService.getArticleSize() < inputid) {
			System.out.println("게시글이 존재하지 않습니다.");
			return;
		} else if (inputid == articleService.getarticleIndex(inputid - 1).id) {

			System.out.printf("번호: %d\n", articleService.getarticleIndex(inputid - 1).id);
			System.out.printf("제목: %s\n", articleService.getarticleIndex(inputid - 1).title);
			System.out.printf("내용: %s\n", articleService.getarticleIndex(inputid - 1).body);
			System.out.printf("날짜: %s\n", articleService.getarticleIndex(inputid - 1).regDate);
			System.out.printf("작성자: %s\n", articleService.getarticleIndex(inputid - 1).memberId);
		}

	}

	private void selectBoard(String com) {
		int input = Integer.parseInt(com.split(" ")[2]);
		if (input > articleService.getBoardSize()) {
			System.out.println(input + "번 게시판은 존재하지 않습니다.");
			return;
		}
		Board board = articleService.getBoardId(input);
		if (board == null) {
			System.out.println("에러에러");
		}
		System.out.println(board.boardName + "(" + board.boardId + "번) 게시판이 선택되었습니다.");
		Container.session.selectedBoardId = board.boardId;

	}

	private void makeBoard() {

		System.out.println("== 게시판 생성 ==");
		System.out.printf("게시판 이름 : ");
		Scanner scan = Container.scanner;
		String boardName = scan.nextLine();

		int i = articleService.boardAdd(boardName);

		System.out.println(boardName + "(" + i + "번) 게시판이 생성되었습니다.");

	}

	private void add() {

		Scanner scan = Container.scanner;
		System.out.printf("제목: ");
		String title = scan.nextLine();

		System.out.printf("내용: ");
		String body = scan.nextLine();
		int num = Container.session.loginedMemberId;

		for (Member member : Container.memberService.getmembers()) {
			if (member.id == num) {
				int i = articleService.add(title, body, member.loginId, Container.session.selectedBoardId);
				System.out.println(member.name + "님이 " + i + "번째 게시물을 생성하였습니다.");
			}

		}

	}

	private void list() {
		List<Article> articles = articleService.getList();
		System.out.println("번호 / 제목 / 작성자 / 게시판");
		int selectBoardId = Container.session.selectedBoardId;
		for (Article article : articles) {
			if (selectBoardId == article.boardId) {
				Board board = articleService.getBoardId(article.boardId);
				System.out.println(
						article.id + " / " + article.title + " / " + article.memberId + " / " + board.boardName);
			}
		}

	}
}
