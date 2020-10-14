package com.sbs.example.easytextboard;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int lastid = 0;

		String[] article_title = new String[3];
		String[] article_body = new String[3];
		int[] article_id = new int[3];

		String article2_title = "";
		String article2_body = "";
		int article2_id = 0;

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

				article_title[lastid] = title;
				article_body[lastid] = body;
				article_id[lastid] = id;
				
				
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

					System.out.printf("%d / %s\n", article_id[0], article_title[0]);
				}
							}
			
			else if (com.startsWith("article detail ")) {

				int inputedid = Integer.parseInt(com.split(" ")[2]);
				
				if(lastid == 0){
					System.out.printf("%d번째 게시물 존재하지 않음\n",inputedid);
					continue;
				}
				else if(lastid >= 1) {
				
				
				System.out.println("== 게시물 상세 ==");
				System.out.printf("번호: %d\n",article_id[inputedid-1]);
				System.out.printf("제목: %s\n",article_title[inputedid-1]);
				System.out.printf("내용: %s\n",article_body[inputedid-1]);
				}
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
