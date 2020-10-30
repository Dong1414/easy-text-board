package com.sbs.example.easytextboard;
import java.util.Scanner;

import com.sbs.example.easytextboard.controller.ArticleController;
import com.sbs.example.easytextboard.controller.MemberController;

public class App {
	
	
	public void run() {
		Scanner scan = new Scanner(System.in);

		MemberController memberController = new MemberController();
		ArticleController articleController = new ArticleController();
		while (true) {

			System.out.printf("명령어) ");
			String command = scan.nextLine();

			if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			} else if (command.startsWith("member ")) {
				memberController.run(scan, command);
			} else if (command.startsWith("article ")) {
				articleController.run(scan, command);
			}
		}

		scan.close();
	}
		
}

