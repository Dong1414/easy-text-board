package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {

	Article[] article = new Article[10];


	public void run() {
		Scanner scanner = new Scanner(System.in);

		int lastid = 0;

		while (true) {

			System.out.printf("명령어: ");

			String com = scanner.nextLine();

			if (com.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				System.out.printf("제목 :");
				String title = scanner.nextLine();

				System.out.printf("내용 :");
				String body = scanner.nextLine();

				int id = lastid + 1;

				article[id - 1] = new Article(id, title, body);

				System.out.printf("%d번째 게시물이 등록되었습니다.\n", id);
				lastid++;
			} else if (com.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (lastid == 0) {
					System.out.println("== 게시물이 존재하지 않습니다. ==");
				}

				for (int i = 0; i < lastid; i++) {

					System.out.printf("번호 : %d\n", article[i].id);
					System.out.printf("제목 : %s\n", article[i].title);
					System.out.printf("내용 : %s\n", article[i].body);
					System.out.printf("=============\n");
				}

			} else if (com.startsWith("article detail ")) {
				int inputid = Integer.parseInt(com.split(" ")[2]);

				if (lastid < inputid) {
					System.out.printf("%d번글이 존재하지 않습니다.\n", inputid);
					continue;
				} else {

					System.out.printf("번호: %d\n", article[inputid - 1].id);
					System.out.printf("제목: %s\n", article[inputid - 1].title);
					System.out.printf("내용: %s\n", article[inputid - 1].body);
				}
			} else if (com.equals("system exit")) {
				System.out.println("프로그램 종료");
				break;
			} else
				System.out.println("명령어 없음");

		}

	}

}
