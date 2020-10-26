package com.sbs.example.easytextboard;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class App {

	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date time = new Date();
	Article[] articles;
	int lastid;
	int articlesSize;
	Member[] members;
	int memberid;
	int membersSize;
	int login = 0;

	public App() {
		
		articles = new Article[2];
		lastid = 0;
		articlesSize = 0;

		for (int i = 0; i < articles.length + 1; i++) {
			add(i + 1, i + 1 + "", i + 1 + "");
			lastid++;
			articlesSize++;
			if (articleSize() == 32) {
				break;
			}
		}
		
		members = new Member[2];
		memberid = 0;
		membersSize = 0;
	}

	int memberSize() {
		return membersSize;
	}
	
	int articleSize() {
		return articlesSize;
	}

	private Article getArticle(int id) {

		int index = move(id);

		if (index == -1) {
			return null;
		}

		return articles[index];
	}

	private int move(int id) {
		if (articleSize() > 0) {
			for (int i = 0; i < articleSize(); i++) {

				if (articles[i].id == id) {

					return i;
				}

			}
		}
		return -1;
	}

	private void remove(int id) {

		int count = move(id);

		if (count != -1) {
			for (int i = count; i < articleSize(); i++) {
				if (i == articles.length - 1) {
					articles[i] = new Article();
				} else {
					articles[i] = articles[i + 1];

				}
			}
			articlesSize--;

			System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
		} else
			System.out.printf("%d번 글은 존재하지 않습니다.", id);
	}

	private void add(int setid, String settitle, String setbody) {
		if (articleSize() >= articles.length) {
			Article[] newarticles = new Article[articles.length * 2];
			for (int i = 0; i < articles.length; i++) {
				newarticles[i] = articles[i];
			}
			articles = newarticles;
		}

		Article article = new Article();

		article.id = setid;
		article.title = settitle;
		article.body = setbody;
		article.regDate = format1.format(time);

		System.out.printf("%d번 게시물이 생성되었습니다.\n", setid);

		articles[articlesSize] = article;

	}

	private void IdAdd(String setid, String setpass, String setname) {
		if (memberSize() >= members.length) {
			Member[] newmembers = new Member[members.length * 2];
			for (int i = 0; i < members.length; i++) {
				newmembers[i] = members[i];
			}
			members = newmembers;
		}

		Member member = new Member();

		member.id = setid;
		member.password = setpass;
		member.name = setname;
		

	

		members[membersSize] = member;
		
		System.out.printf("아이디 : %s\n비밀번호 : %s\n이름 : %s 가입이 완료되었습니다.\n", members[membersSize].id,members[membersSize].password,members[membersSize].name);
		
	}
	
	private void Search(String input, int page) {

		int count = 0;

		for (int i = articleSize() - 1; i >= 0; i--) {

			if (articles[i].body.contains(input)) {
				count++;
			}
		}
		Article[] searcharticles = new Article[count];
		int j = 0;
		for (int i = 0; i < articleSize(); i++) {

			if (articles[i].body.contains(input)) {
				searcharticles[j] = articles[i];
				j++;
			}
		}
		int inpage = 10;
		int startPos = searcharticles.length-1;
		
		startPos -= (page - 1) * inpage;
		
		int endPos = startPos - (inpage - 1);
		
		if(endPos < 0)endPos=0;
		if (startPos < 0) {
			System.out.printf("%d 페이지는 존재하지 않습니다.",page);
			return;
		}
		System.out.printf("== %d 페이지 ==\n",page);
		for (int i = startPos; i >= endPos; i--) {
			
			System.out.printf("%d / %s\n", searcharticles[i].id,searcharticles[i].title);			
		
		}
		if (count == 0) {
			System.out.printf("%s가 포함된 게시물은 존재하지 않습니다.\n", input);
		}
	}

	public void run() {

		Scanner scan = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어: ");
			String com = scan.nextLine();

			if (com.equals("article add")) {

				System.out.printf("제목: ");
				String title = scan.nextLine();

				System.out.printf("내용: ");
				String body = scan.nextLine();

				int id = lastid + 1;
				lastid = id;
				
				add(id, title, body);

				articlesSize++;

			}else if(com.equals("member join")){
				
				System.out.printf("로그인 아이디 : ");
				String member = scan.nextLine();
				
				System.out.printf("로그인 비번 : ");
				String pass = scan.nextLine();
				
				System.out.printf("이름 : ");
				String name = scan.nextLine();
				int id = memberid +1;
				memberid = id;
			
				IdAdd(member, pass, name);
				
				membersSize++;
				
				
			}
			else if(com.equals("member login")){
				System.out.printf("로그인 아이디 : ");
				String member = scan.nextLine();
				
				System.out.printf("로그인 비번 : ");
				String pass = scan.nextLine();
		
				for(int i = 0; i < members.length; i++) {
					if(members[i].id.equals(member)){
						if(members[i].password.equals(pass)) {
							System.out.printf("%s님이 환영합니다.\n",members[i].name);
							login = i + 1;
							break;
						}else System.out.println("비밀번호가 일치하지 않습니다.");
					}
					if(i == members.length-1) {System.out.println("아이디가 존재하지 않습니다.");}
				}
			}
			else if(com.equals("member logout")) {
				if(login > 0) {
					System.out.printf("%s님이 로그아웃\n",members[login-1].name);
					login = 0;
				}else System.out.println("로그인이 되어있지 않습니다.");
			}

			else if (com.startsWith("article delete ")) {				
				
				
				int inputid = Integer.parseInt(com.split(" ")[2]);										
				
				
				
				Article article = getArticle(inputid);

				if (article == null) {
					System.out.printf("%d번글은 존재하지 않습니다.\n", inputid);
					continue;
				} else
					remove(inputid);

			} else if (com.startsWith("article search ")) {
				String[] commandBits = com.split(" ");

				String inputString = commandBits[2];

				int page = 1;

				if (commandBits.length >= 4) {
					page = Integer.parseInt(commandBits[3]);
				}

				if (page <= 1) {
					page = 1;
				}
				System.out.printf("== %s가 포함된 게시물 ==\n", inputString);

				if (articleSize() <= 0) {
					System.out.println("== 게시물이 존재하지 않습니다. ==");

				} else
					Search(inputString,page);

			}

			else if (com.startsWith("article modify ")) {
				int inputid = Integer.parseInt(com.split(" ")[2]);
				Article article = getArticle(inputid);

				if (articleSize() <= 0) {
					System.out.println("게시물이 존재하지 않습니다");
					continue;
				} else if (article == null) {
					System.out.printf("%d글이 존재하지 않습니다.\n", inputid);
					continue;
				}
				System.out.printf("%d번 글 수정\n", inputid);
				System.out.printf("새 제목: ");
				article.title = scan.nextLine();
				System.out.printf("새 내용: ");
				article.body = scan.nextLine();
				article.regDate = format1.format(time);
				articles[move(inputid)] = article;
				
			} else if (com.startsWith("article list ")) {
				int inputid = Integer.parseInt(com.split(" ")[2]);
				if (articleSize() == 0 || articleSize() / 10 + 1 < inputid) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				System.out.println("== 게시물 리스트 ==");
				System.out.println("번호 / 제목");

				int itemsInAPage = 10; // 표시할 범위 값
				int startPos = articleSize() - 1; 
				startPos -= (inputid - 1) * itemsInAPage;				
				int endPos = startPos - (itemsInAPage - 1);
				
				if (endPos < 0) {
					endPos = 0;
				}
				
				if (startPos < 0) {
					System.out.printf("%d 페이지는 존재하지 않습니다.",startPos);
					continue;
				}

				for (int i = startPos; i >= endPos; i--) {
					System.out.printf("%d / %s\n", articles[i].id, articles[i].title);
				}

			} else if (com.startsWith("article detail ")) {
				int inputid = Integer.parseInt(com.split(" ")[2]);

				System.out.println("== 게시물 상세 ==");

				Article article = getArticle(inputid);

				if (article == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputid);
					continue;
				}

				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);
				System.out.printf("생성 시간 : %s\n\n", article.regDate);

			} else if (com.equals("system exit")) {
				System.out.println("프로그램 종료");
				break;
			} else
				System.out.println("명령어 없음");

		}
		scan.close();
	}

}
