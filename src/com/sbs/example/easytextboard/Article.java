package com.sbs.example.easytextboard;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
	
	public int id;
	public String title;
	public String body;
	public String regDate;
	
	
	
	public Article(int id, String title, String body) {
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = format1.format(time);
	}
	
}