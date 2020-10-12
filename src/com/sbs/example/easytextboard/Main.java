package com.sbs.example.easytextboard;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);	
		System.out.print("명령어 입력: ");
		
		String command = scanner.nextLine();

		if (command.equals("text add")) {
			System.out.println("텍스트 추가");
		} else if (command.equals("text list")) {
			System.out.println("텍스트 리스트");
		} else {
			System.out.println("존재하지 않음");
		}

	}

}
