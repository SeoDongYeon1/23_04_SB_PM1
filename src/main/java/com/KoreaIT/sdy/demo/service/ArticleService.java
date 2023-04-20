package com.KoreaIT.sdy.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.sdy.demo.repository.ArticleRepository;
import com.KoreaIT.sdy.demo.util.Ut;
import com.KoreaIT.sdy.demo.vo.Article;
import com.KoreaIT.sdy.demo.vo.ResultData;

@Service
public class ArticleService {
	@Autowired
	ArticleRepository articleRepository;
	
	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}

	public ResultData<Integer> writeArticle(String title, String body) {
		articleRepository.writeArticle(title, body);
		
		int id = articleRepository.getLastInsertId();
		
		return ResultData.from("S-1", Ut.f("%d번 게시글이 생성되었습니다.", id));
	}

	public Article getArticleById(int id) {
		return articleRepository.getArticleById(id);
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public ResultData<Article> actorCanModifyRd(int id, String title, String body) {
		articleRepository.modifyArticle(id, title,body);
		
		Article article = articleRepository.getArticleById(id);
		
		return ResultData.from("S-1", Ut.f("%d번 글이 수정되었습니다.", id), article);
	}
	
}
