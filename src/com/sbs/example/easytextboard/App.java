package com.sbs.example.easytextboard;

import java.util.Scanner;

import com.sbs.example.easytextboard.controller.ArticleController;
import com.sbs.example.easytextboard.controller.Controller;
import com.sbs.example.easytextboard.controller.MemberController;

public class App {
	MemberController memberController;
	ArticleController articleController;

	public App() {
		memberController = new MemberController();
		articleController = new ArticleController();
	}

	public void run() {
		Scanner scan = new Scanner(System.in);

		while (true) {

			System.out.printf("명령어) ");
			String cmd = scan.nextLine();

			if (cmd.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			}

			Controller controller = getByController(cmd);
			if (controller != null) {
				controller.doCommand(cmd);
			}

		}

		scan.close();
	}

	private Controller getByController(String cmd) {
		if (cmd.startsWith("member ")) {
			return memberController;
		} else if (cmd.startsWith("article ")) {
			return articleController;
		}
		return null;
	}

}
