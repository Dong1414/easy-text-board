package com.sbs.example.easytextboard;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int i = 1;
		while (true) {
			System.out.print("명령어 입력: ");
			String com = scanner.nextLine();

			if (com.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				String title, body;
				System.out.print("제목: ");
				title = scanner.nextLine();
				System.out.print("내용: ");
				body = scanner.nextLine();

				System.out.println("== 생성된 게시물 내용 ==");
				System.out.println("번호 : " + i);
				System.out.println("제목 : " + title);
				System.out.println("내용 : " + body);
				i++;

			} else if (com.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");
			} else if (com.equals("system exit")) {
				System.out.println("== 프로그램을 종료합니다 ==");
				break;
			} else
				System.out.println("== 존재하지 않는 명령어 입니다 ==");
		}
		scanner.close();
	}
}
