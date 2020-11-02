package com.sbs.example.easytextboard.container;

import com.sbs.example.easytextboard.dao.ArticleDao;
import com.sbs.example.easytextboard.dao.MemberDao;
import com.sbs.example.easytextboard.service.ArticleService;
import com.sbs.example.easytextboard.service.MemberService;
import com.sbs.example.easytextboard.session.Session;

public class Container {
	public static Session session;
	public static ArticleService articleservice;
	public static MemberService memberservice;
	public static MemberDao memberdao;
	public static ArticleDao articledao;
	
	static {
		session = new Session();
		memberdao = new MemberDao();
		articledao = new ArticleDao();
		memberservice = new MemberService();
		articleservice = new ArticleService();
	}
	
}