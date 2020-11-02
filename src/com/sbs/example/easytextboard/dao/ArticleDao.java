package com.sbs.example.easytextboard.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dto.Article;

public class ArticleDao {

	private List<Article> articles;

	private int lastid;

	public ArticleDao() {
		articles = new ArrayList<Article>();
		lastid = 0;

		for (int i = 0; i < 32; i++) {
			add("제목" + (i + 1), "내용" + (i + 1), i % 2 == 0 ? Container.memberdao.getmembers().get(0).id : Container.memberdao.getmembers().get(1).id);

		}
		
	}

	public int add(String title, String body, String memberid) {

		Article article = new Article();

		article.id = lastid + 1;
		article.title = title;
		article.body = body;
		article.memberId = memberid;

		articles.add(article);
		lastid = article.id;
		return article.id;
	}

	public void List(int page) {
		if (page <= 1) {
			page = 1;
		}
		int term = 10;
		int start = articles.size() - 1;

		if (page > start / 10 +1) {
			System.out.printf("%d페이지는 존재하지 않습니다.\n", page);
			return;
		}
		start -= (page - 1) * term;
		int end = start - (term - 1);

		if (end <= 0) {
			end = 0;
		}

		for (int i = start; i >= end; i--) {
			System.out.printf("%d / %s / %s\n", articles.get(i).id, articles.get(i).title,articles.get(i).memberId);
		}
	}

	public void delete(int input) {

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

	public void modify(int input) {

		Scanner scan = new Scanner(System.in);

		System.out.printf("새 제목: ");
		String title = scan.nextLine();
		System.out.printf("새 내용: ");
		String body = scan.nextLine();
		Article article = new Article();

		article.id = articles.get(input - 1).id;
		article.memberId = articles.get(input - 1).memberId;
		article.title = title;
		article.body = body;
		articles.set(input - 1, article);
		System.out.printf("%d번 글이 변경되었습니다.\n", input);

	}

	public void search(String inputbody, int page) {
		if (page <= 1) {
			page = 1;
		}
		System.out.printf("== %s 가 포함된 게시물 ==\n", inputbody);
		List<Article> listarticle = new ArrayList<Article>();

		for (int i = 0; i <= articles.size() - 1; i++) {
			if (articles.get(i).body.contains(inputbody)) {

				listarticle.add(articles.get(i));
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

	public int getlastid() {
		return lastid;
	}

	public List<Article> getarticles() {
		return articles;
	}

	public int getarticlesize() {
		return articles.size();
	}

	public Article getarticleIndex(int i) {
		return articles.get(i);
	}

	
}
