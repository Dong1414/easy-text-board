package com.sbs.example.easytextboard.service;

import java.util.List;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dao.ArticleDao;
import com.sbs.example.easytextboard.dto.Article;
import com.sbs.example.easytextboard.dto.Board;

public class ArticleService {
	
	private ArticleDao articledao;
	
	
	public ArticleService() {
		articledao = Container.articleDao;
		
	
		
		
	}
	
	public int add(String title, String body, String memberId,int boardId) {
		return articledao.add(title, body, memberId,boardId);		
	}

	public int getLastId() {
 
		return articledao.getLastId();
	}

	public int getArticleSize() {
	
		return articledao.getArticleSize();
	}

	public List<Article> getList() {
		return articledao.getList();		
	}

	public Article getarticleIndex(int i) {
		return articledao.getArticleIndex(i);
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

	public int boardAdd(String boardName) {
		
		return articledao.boardAdd(boardName);
		
	}

	public int getBoardSize() {
		return articledao.getBoardSize();
	
	}

	public Board getBoardId(int input) {
		
		return articledao.getBoardId(input);
				
	}
	
	

}
