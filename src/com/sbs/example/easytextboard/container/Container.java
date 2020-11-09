package com.sbs.example.easytextboard.container;

import java.util.Scanner;

import com.sbs.example.easytextboard.dao.ArticleDao;
import com.sbs.example.easytextboard.dao.MemberDao;
import com.sbs.example.easytextboard.service.ArticleService;
import com.sbs.example.easytextboard.service.MemberService;
import com.sbs.example.easytextboard.session.Session;

public class Container {
	public static Scanner scanner;
	public static Session session;
	
	public static ArticleService articleService;
	public static MemberService memberService;
	public static MemberDao memberDao;
	
	public static ArticleDao articleDao;
	
	static {
		scanner = new Scanner(System.in);
		session = new Session();
		
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		
		memberService = new MemberService();
		articleService = new ArticleService();
	}
	
}