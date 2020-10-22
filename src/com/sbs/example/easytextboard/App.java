package com.sbs.example.easytextboard;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class App {

	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date time = new Date();
	Article[] articles;
	int lastid;
	int articlesSize;

	public App() {

		articles = new Article[2];
		lastid = 0;
		articlesSize = 0;

		for (int i = 0; i < articles.length + 1; i++) {
			add(i + 1, i + 1 + "", i + 1 + "");
			lastid++;
			articlesSize++;
			if (articleSize() == 32) {
				break;
			}
		}

	}

	int articleSize() {
		return articlesSize;
	}

	private Article getArticle(int id) {

		int index = move(id);

		if (index == -1) {
			return null;
		}

		return articles[index];
	}

	private int move(int id) {
		if (articleSize() > 0) {
			for (int i = 0; i < articleSize(); i++) {

				if (articles[i].id == id) {

					return i;
				}

			}
		}
		return -1;
	}

	private void remove(int id) {

		int count = move(id);

		if (count != -1) {
			for (int i = count; i < articleSize(); i++) {
				if (i == articles.length - 1) {
					articles[i] = new Article();
				} else {
					articles[i] = articles[i + 1];

				}
			}
			articlesSize--;

			System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
		} else
			System.out.printf("%d번 글은 존재하지 않습니다.", id);
	}

	private void add(int setid, String settitle, String setbody) {
		if (articleSize() >= articles.length) {
			Article[] newarticles = new Article[articles.length * 2];
			for (int i = 0; i < articles.length; i++) {
				newarticles[i] = articles[i];
			}
			articles = newarticles;
		}

		Article article = new Article();

		article.id = setid;
		article.title = settitle;
		article.body = setbody;
		article.regDate = format1.format(time);

		System.out.printf("%d번 게시물이 생성되었습니다.\n", setid);

		articles[articlesSize] = article;

	}

	private void Search(String input) {

		int count = 0;

		for (int i = articleSize() - 1; i >= 0; i--) {

			if (articles[i].body.contains(input)) {

				System.out.printf("번호 : %d\n", articles[i].id);
				System.out.printf("제목 : %s\n", articles[i].title);
				System.out.printf("내용 : %s\n", articles[i].body);
				System.out.printf("생성 시간 : %s\n\n", articles[i].regDate);
				count++;
			}
		}
		if (count == 0) {
			System.out.printf("%s가 포함된 게시물은 존재하지 않습니다.\n", input);
		}
	}

	public void run() {

		Scanner scan = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어: ");
			String com = scan.nextLine();

			if (com.equals("article add")) {

				System.out.printf("제목: ");
				String title = scan.nextLine();

				System.out.printf("내용: ");
				String body = scan.nextLine();

				int id = lastid + 1;
				lastid = id;

				add(id, title, body);

				articlesSize++;

			}

			else if (com.startsWith("article delete ")) {
				int inputid = Integer.parseInt(com.split(" ")[2]);
				Article article = getArticle(inputid);

				if (article == null) {
					System.out.printf("%d번글은 존재하지 않습니다.\n", inputid);
					continue;
				} else
					remove(inputid);

			} else if (com.startsWith("article search ")) {
				String inputString = com.split(" ")[2];
				System.out.printf("== %s가 포함된 게시물 ==\n", inputString);

				if (articleSize() <= 0) {
					System.out.println("== 게시물이 존재하지 않습니다. ==");

				} else
					Search(inputString);

			}

			else if (com.startsWith("article modify ")) {
				int inputid = Integer.parseInt(com.split(" ")[2]);
				Article article = getArticle(inputid);

				if (articleSize() <= 0) {
					System.out.println("게시물이 존재하지 않습니다");
					continue;
				} else if (article == null) {
					System.out.printf("%d글이 존재하지 않습니다.\n", inputid);
					continue;
				}
				System.out.printf("%d번 글 수정\n", inputid);
				System.out.printf("새 제목: ");
				article.title = scan.nextLine();
				System.out.printf("새 내용: ");
				article.body = scan.nextLine();
				article.regDate = format1.format(time);
				articles[move(inputid)] = article;

			} else if (com.startsWith("article list ")) {
				int inputid = Integer.parseInt(com.split(" ")[2]);
				if (articleSize() == 0 || articleSize() / 10 + 1 < inputid) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				System.out.println("== 게시물 리스트 ==");
				System.out.println("번호 / 제목");

				getlist(inputid);

			} else if (com.startsWith("article detail ")) {
				int inputid = Integer.parseInt(com.split(" ")[2]);

				System.out.println("== 게시물 상세 ==");

				Article article = getArticle(inputid);

				if (article == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputid);
					continue;
				}

				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);
				System.out.printf("생성 시간 : %s\n\n", article.regDate);

			} else if (com.equals("system exit")) {
				System.out.println("프로그램 종료");
				break;
			} else
				System.out.println("명령어 없음");

		}
		scan.close();
	}

	private void getlist(int inputid) {

		int[] listid = new int[articleSize() / 10 + 1];

		for (int i = 0; i < listid.length; i++) {
			listid[i] = i + 1;
		}
		while (true) {
			if (inputid == listid[inputid - 1]) {
				int j = articleSize() - (inputid * 10);
				for (int i = 1; i >= j; i--) {

					Article article = articles[i];
					System.out.printf("%d / %s\n", article.id, article.title);
					if (j == 0)
						break;

				}
				break;

			}

		}

	}
}