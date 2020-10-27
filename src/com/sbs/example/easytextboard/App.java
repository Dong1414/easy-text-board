package com.sbs.example.easytextboard;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
	List<Article> articles = new ArrayList<Article>();
	List<Member> members = new ArrayList<Member>();
	int lastid;
	int memberid;
	int login = 0;

	public App() {
		for (int i = 0; i < 32; i++) {
			articles.add(new Article(i + 1, i + 1 + "", i + 1 + ""));
			lastid++;
		}
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
		articles.set(input - 1, new Article(articles.get(input - 1).id, title, body));
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
				listarticle.add(new Article(articles.get(i).id, articles.get(i).title, articles.get(i).body));
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

	public void run() {

		Scanner scan = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어: ");
			String com = scan.nextLine();
			if (com.equals("system exit")) {
				System.out.println("프로그램종료");
				break;
			} else if (com.equals("article add")) {

				System.out.printf("제목: ");
				String title = scan.nextLine();

				System.out.printf("내용: ");
				String body = scan.nextLine();

				int id = lastid + 1;
				lastid = id;

				articles.add(new Article(id, title, body));

			} else if (com.startsWith("article list ")) {

				int page = Integer.parseInt(com.split(" ")[2]);
				if (articles.size() == 0) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
				} else {

					System.out.println("== 게시물 리스트 ==");
					List(page);

				}
			} else if (com.startsWith("article detail ")) {
				int inputid = Integer.parseInt(com.split(" ")[2]);
				if (articles.size() == 0 || articles.size() <= inputid) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
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
					continue;
				} else {
					delete(inputid);
				}

			} else if (com.startsWith("article modify ")) {
				int inputid = Integer.parseInt(com.split(" ")[2]);
				if (articles.size() == 0 || articles.size() < inputid) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
				} else if (articles.get(inputid - 1).id == inputid)
					modify(inputid);

			} else if (com.startsWith("article search ")) {
				String[] commandBit = com.split(" ");

				String inputbody = commandBit[2];

				if (articles.size() <= 0) {
					System.out.println("페이지가 존재하지 않습니다.");
					continue;
				} else if (commandBit.length > 3) {
					int page = 1;
					page = Integer.parseInt(commandBit[3]);
					search(inputbody, page);
				} else {
					System.out.printf("== %s 가 포함된 게시물 ==\n", inputbody);
					List<Article> listarticle = new ArrayList<Article>();

					for (int i = 0; i <= articles.size() - 1; i++) {
						if (articles.get(i).body.contains(inputbody)) {
							listarticle
									.add(new Article(articles.get(i).id, articles.get(i).title, articles.get(i).body));
						}
					}
					if (listarticle.size() <= 0) {
						System.out.printf("%s가 포함된 게시물이 존재하지 않습니다.\n", inputbody);
						continue;
					}
					for (int i = listarticle.size() - 1; i >= 0; i--) {
						System.out.printf("%d / %s\n", listarticle.get(i).id, listarticle.get(i).title);
					}
				}

			} else if (com.equals("member join")) {
				System.out.printf("로그인 아이디: ");
				String id = scan.nextLine();

				System.out.printf("비밀번호: ");
				String password = scan.nextLine();

				System.out.printf("이름: ");
				String name = scan.nextLine();

				members.add(new Member(id, password, name));
				memberid++;
			} else if (com.equals("member login")) {
				System.out.printf("로그인 아이디: ");
				String id = scan.nextLine();

				System.out.printf("비밀번호: ");
				String password = scan.nextLine();

				Login(id, password);

			} else if (com.equals("member logout")) {
				if (login > 0) {
					System.out.printf("%s님이 로그아웃 하였습니다.\n", members.get(login - 1).name);
					login = 0;
				} else
					System.out.println("현재 사용자가 없습니다.");

			} else if (com.equals("member whoami")) {
				if (login > 0) {
					System.out.printf("현재 사용자 : %s / %s\n", members.get(login - 1).id, members.get(login - 1).name);
					login = 0;
				} else
					System.out.println("현재 사용자가 없습니다.");

			}

		}
		scan.close();
	}

	private void Login(String id, String password) {
		for (int i = 0; i < members.size(); i++) {
			if (id.equals(members.get(i).id)) {
				if (members.get(i).password.equals(password)) {
					System.out.printf("%s님 환영합니다.\n", members.get(i).name);
					login = i + 1;
					return;
				} else {
					System.out.println("비밀번호가 일치하지 않습니다.");
					return;
				}
			}
			if (i == members.size() - 1) {
				System.out.println("아이디가 일치하지 않습니다.");
			}
		}

	}

}