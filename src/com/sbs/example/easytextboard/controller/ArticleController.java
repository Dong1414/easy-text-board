package com.sbs.example.easytextboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.dto.Article;
import com.sbs.example.easytextboard.dto.Member;

public class ArticleController {
	List<Article> articles;
	int lastid;

	public ArticleController() {
		articles = new ArrayList<Article>();
		lastid = 0;
		for (int i = 0; i < 32; i++) {
			add("제목" + (i + 1), "내용" + (i + 1));
			
		}
		lastid++;
	}

	private int add(String title, String body) {

		Article article = new Article();

		article.id = lastid + 1;
		article.title = title;
		article.body = body;

		articles.add(article);
		lastid = article.id;		
		return article.id;
	}

	private void List(int page) {
		if (page <= 1) {
			page = 1;
		}
		int term = 10;
		int start = articles.size() - 1;

		if (page > start / 10) {
			System.out.printf("%d페이지는 존재하지 않습니다.\n", page);
			return;
		}
		start -= (page - 1) * term;
		int end = start - (term - 1);

		if (end <= 0) {
			end = 0;
		}

		for (int i = start; i >= end; i--) {
			System.out.printf("%d / %s\n", articles.get(i).id, articles.get(i).title);
		}
	}

	private void delete(int input) {

		for (int i = 0; i < articles.size(); i++) {
			if (articles.get(i).id == input) {
				articles.remove(i);
				System.out.printf("%d번 글이 삭제되었습니다.\n", i + 1);
				for (int j = i; j < articles.size(); j++) {
					articles.get(j).id -= 1;
				}
			}

		}
		lastid--;

	}

	private void modify(int input) {

		Scanner scan = new Scanner(System.in);

		System.out.printf("새 제목: ");
		String title = scan.nextLine();
		System.out.printf("새 내용: ");
		String body = scan.nextLine();
		Article article = new Article();

		article.id = lastid + 1;
		article.title = title;
		article.body = body;
		articles.set(input - 1, article);
		System.out.printf("%d번 글이 변경되었습니다.\n", input);

	}

	private void search(String inputbody, int page) {
		if (page <= 1) {
			page = 1;
		}
		System.out.printf("== %s 가 포함된 게시물 ==\n", inputbody);
		List<Article> listarticle = new ArrayList<Article>();

		for (int i = 0; i <= articles.size() - 1; i++) {
			if (articles.get(i).body.contains(inputbody)) {

				listarticle.add(articles.get(i));
			}
		}
		int term = 10;
		int start = listarticle.size() - 1;
		start -= (page - 1) * term;
		int end = start - (term - 1);

		if (start < 0) {
			System.out.printf("%s가 포함된 게시물이 존재하지 않습니다.\n", inputbody);
			return;
		}

		if (end <= 0) {
			end = 0;
		}
		for (int i = start; i >= end; i--) {
			System.out.printf("%d / %s\n", listarticle.get(i).id, listarticle.get(i).title);
		}
	}

	public void run(Scanner scan, String com) {

		if (com.equals("article add")) {

			System.out.printf("제목: ");
			String title = scan.nextLine();

			System.out.printf("내용: ");
			String body = scan.nextLine();			

			add(title, body);
			System.out.println(lastid + "번째 게시물 생성되었습니다.");

		} else if (com.startsWith("article list ")) {

			int page = Integer.parseInt(com.split(" ")[2]);
			if (articles.size() == 0) {
				System.out.println("게시글이 존재하지 않습니다.");
				return;
			} else {

				System.out.println("== 게시물 리스트 ==");
				List(page);

			}
		} else if (com.startsWith("article detail ")) {
			int inputid = Integer.parseInt(com.split(" ")[2]);
			if (articles.size() == 0 || articles.size() < inputid) {
				System.out.println("게시글이 존재하지 않습니다.");
				return;
			} else if (inputid == articles.get(inputid - 1).id) {
				
				System.out.printf("번호: %d\n", articles.get(inputid - 1).id);
				System.out.printf("제목: %s\n", articles.get(inputid - 1).title);
				System.out.printf("내용: %s\n", articles.get(inputid - 1).body);
				System.out.printf("날짜: %s\n", articles.get(inputid - 1).regDate);
			}

		} else if (com.startsWith("article delete ")) {
			int inputid = Integer.parseInt(com.split(" ")[2]);
			if (articles.size() == 0 || articles.size() < inputid) {
				System.out.println("게시글이 존재하지 않습니다.");
				return;
			} else {
				delete(inputid);
			}

		} else if (com.startsWith("article modify ")) {
			int inputid = Integer.parseInt(com.split(" ")[2]);
			if (articles.size() == 0 || articles.size() < inputid) {
				System.out.println("게시글이 존재하지 않습니다.");
				return;
			} else if (articles.get(inputid - 1).id == inputid)
				modify(inputid);

		} else if (com.startsWith("article search ")) {
			String[] commandBit = com.split(" ");

			String inputbody = commandBit[2];

			if (articles.size() <= 0) {
				System.out.println("페이지가 존재하지 않습니다.");
				return;
			} else if (commandBit.length > 3) {
				int page = 1;
				page = Integer.parseInt(commandBit[3]);
				search(inputbody, page);
			} else {
				System.out.printf("== %s 가 포함된 게시물 ==\n", inputbody);
				List<Article> listarticle = new ArrayList<Article>();

				for (int i = 0; i <= articles.size() - 1; i++) {
					if (articles.get(i).body.contains(inputbody)) {
						listarticle.add(articles.get(i));
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
