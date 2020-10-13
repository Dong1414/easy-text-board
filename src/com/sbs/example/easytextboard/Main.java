package com.sbs.example.easytextboard;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String com;

		int article1_id = 0;
		String article1_title = "";
		String article1_body = "";

		int article2_id = 0;
		String article2_title = "";
		String article2_body = "";

		
		int lastid = 0;
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어: ");
			com = scanner.nextLine();

			if (com.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				String title = "", body = "";

				int id = lastid + 1;
				System.out.printf("제목: ");
				title = scanner.nextLine();

				System.out.printf("내용: ");
				body = scanner.nextLine();

				if (id == 1) {
					article1_id = id;
					article1_title = title;
					article1_body = body;
				}

				if (id == 2) {
					article2_id = id;
					article2_title = title;
					article2_body = body;
				}

				lastid = id;
				System.out.printf("%d 게시물이 등록되었습니다.\n", id);
			
			} else if (com.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (lastid == 0) {
					System.out.printf("게시물이 없음.\n");
					continue;
				}
				
					System.out.println("번호 / 제목");
				
				if (lastid >= 1) {

					System.out.printf("%d / %s\n", article1_id, article1_title);
				}
				if (lastid >= 2) {
					System.out.printf("%d / %s\n", article2_id, article2_title);
				}

			} else if (com.equals("system exit")) {
				System.out.println("프로그램 종료.");
				break;
			}
			else System.out.println("명령어 없음.");

		}
		scanner.close();
	}
}
