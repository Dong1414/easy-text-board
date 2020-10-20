package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {

	Article[] articles = new Article[100];
	int lastid = 0;
	int articlesCount = 0;
	public Article getArticle(int id) {

		if (id < 1) {
			return null;
		} else if (id > lastid) {
			return null;
		}

		return articles[id - 1];
	}

	public int move(int id) {
		
		for(int i = 0; i < articles.length ; i++) {
			if(articles[i].id == id)return i;
			
		}
		return -1;
	}
	
	public void remove(int id) {
		
		int count = move(id);
		
		for (int i = count ; i < articlesCount; i++) {
			articles[i] = articles[i+1];
			articles[i].id = i+1;
			articles[i+1] = new Article();
		}
		articlesCount--;

		System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);

	}
	
	public void run() {

		for (int i = 0; i < articles.length; i++) {
			articles[i] = new Article();

		}

		Scanner scan = new Scanner(System.in);

		

		while (true) {
			System.out.printf("명령어: ");
			String com = scan.nextLine();

			if (com.equals("article add")) {

				lastid = articlesCount;

				System.out.printf("제목: ");
				String title = scan.nextLine();

				System.out.printf("내용: ");
				String body = scan.nextLine();

				int id = lastid + 1;
				lastid = id;

				Article article = getArticle(id);

				article.id = id;
				article.title = title;
				article.body = body;

				articlesCount++;

			} else if (com.startsWith("article delete ")) {
				int inputid = Integer.parseInt(com.split(" ")[2]);
				if (inputid > articlesCount) {
					System.out.printf("%d번글은 존재하지 않습니다.\n", inputid);
				} else remove(inputid); 
					
									

			}

			else if (com.equals("article list")) {

				if (articlesCount == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					System.out.println("== 게시물 리스트 ==");
					System.out.println("번호 / 제목");
					for (int i = 0; i < articlesCount; i++) {

						System.out.printf("%d / %s\n", articles[i].id, articles[i].title);
					}

				}

			} else if (com.startsWith("article detail ")) {
				int inputid = Integer.parseInt(com.split(" ")[2]);

				if (inputid > articlesCount) {
					System.out.printf("%d번 글이 존재하지 않습니다.\n", inputid);
				} else {
					Article article = getArticle(inputid);
					System.out.println("== 게시물 상세 ==");
					System.out.printf("번호: %d\n", article.id);
					System.out.printf("제목: %s\n", article.title);
					System.out.printf("내용: %s\n", article.body);

				}
			} else if (com.equals("system exit")) {
				System.out.println("프로그램 종료");
				break;
			} else
				System.out.println("명령어 없음");

		}
		scan.close();
	}

}
