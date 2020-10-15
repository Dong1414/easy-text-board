package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {

	Article article1 = new Article();
	Article article2 = new Article();


	
	
	
	public void setarticle(int id, String title, String body) {
		
		article1.id = id;
		article1.title = title;
		article1.body = body;
	}
	
	
	public Article getarticle(int id) {

		if (id == 1) {
			return article1;
		} else if (id == 2) {
			return article2;
		}
		return null;
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);

		int lastid = 0;

		while (true) {
			System.out.printf("명령어 입력: ");
			String com = scanner.nextLine();

			if (com.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				String title = "", body = "";
				int id = lastid + 1;

				System.out.printf("제목: ");
				title = scanner.nextLine();

				System.out.printf("내용: ");
				body = scanner.nextLine();
				
				Article article = new Article();
				
				
				if (id == 1) {
					article1.title = title;
					article1.body = body;
					article1.id = id;
				}
				if (id == 2) {
					article2.title = title;
					article2.body = body;
					article2.id = id;
				}
				System.out.printf("%d번재 게시물 등록\n", id);
				lastid = id;

			}

			else if (com.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");
				
				
				
				if (lastid == 0) {
					System.out.println("== 게시물 없음 ==");
					continue;
				}
				System.out.println("번호 / 제목");
				if (lastid >= 1) {

					System.out.printf("%d / %s\n", article1.id, article1.title);
				}
				if (lastid >= 2) {

					System.out.printf("%d / %s\n", article2.id, article2.title);
				}

			}

			else if (com.startsWith("article detail ")) {

				int inputedid = Integer.parseInt(com.split(" ")[2]);

				Article selectedArticle = getarticle(inputedid);

				if (selectedArticle == null || selectedArticle.id == 0) {
					System.out.printf("%d번째 게시물 존재하지 않음\n", inputedid);
					continue;
				}
				System.out.println("== 게시물 상세 ==");
				System.out.printf("번호: %d\n", selectedArticle.id);
				System.out.printf("제목: %s\n", selectedArticle.title);
				System.out.printf("내용: %s\n", selectedArticle.body);

			}

			else if (com.equals("system exit")) {
				System.out.println("프로그램 종료");
				break;
			} else
				System.out.println("명령어 없음");

		}
		scanner.close();

	}

}
