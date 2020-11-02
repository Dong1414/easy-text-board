package com.sbs.example.easytextboard.service;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dao.ArticleDao;
import com.sbs.example.easytextboard.dto.Article;

public class ArticleService {

	ArticleDao articledao = Container.articledao;
	
	public void add(String title, String body, String memberid) {
		articledao.add(title, body, memberid);		
	}

	public int getlastid() {
 
		return articledao.getlastid();
	}

	public int getarticlesize() {
	
		return articledao.getarticlesize();
	}

	public void List(int page) {
		articledao.List(page);		
	}

	public Article getarticleIndex(int i) {
		return articledao.getarticleIndex(i);
	}

	public void delete(int inputid) {
		articledao.delete(inputid);
		
	}

	public void modify(int inputid) {
		articledao.modify(inputid);
		
	}

	public void search(String inputbody, int page) {
		articledao.search(inputbody, page);
		
	}
	
	

}
