package com.KoreaIT.sdy.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.sdy.demo.repository.ArticleRepository;
import com.KoreaIT.sdy.demo.vo.Article;

@Service
public class ArticleService {
	@Autowired
	ArticleRepository articleRepository;
	
	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}
	
}
