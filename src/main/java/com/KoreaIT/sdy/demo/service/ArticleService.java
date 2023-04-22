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

	public ResultData<Integer> writeArticle(String title, String body, int memberId) {
		articleRepository.writeArticle(title, body, memberId);
		
		int id = articleRepository.getLastInsertId();
		
		return ResultData.from("S-1", Ut.f("%d번 게시글이 생성되었습니다.", id), id);
	}

	public Article getArticleById(int id) {
		return articleRepository.getArticleById(id);
	}
	
	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}
	
	private void updateForPrintData(int actorId, Article article) {
		if (article == null) {
			return;
		}

		ResultData<String> actorCanDeleteRd = actorCanDelete(actorId, article);
		article.setActorCanDelete(actorCanDeleteRd.isSuccess());
	}

	private ResultData<String> actorCanDelete(int actorId, Article article) {
		if (article == null) {
			return ResultData.from("F-1", "게시물이 존재하지 않습니다");
		}

		if (article.getMemberId() != actorId) {
			return ResultData.from("F-2", "해당 게시물에 대한 권한이 없습니다");
		}

		return ResultData.from("S-1", "삭제 가능");
	}

	public ResultData<Article> actorCanModifyRd(int id, String title, String body) {
		articleRepository.modifyArticle(id, title,body);
		
		Article article = articleRepository.getArticleById(id);
		
		return ResultData.from("S-1", Ut.f("%d번 글이 수정되었습니다.", id), article);
	}

	public Article getForPrintArticle(int actorId, int id) {
		Article article = articleRepository.getForPrintArticle(id);
		
		updateForPrintData(actorId, article);
		return article;
	}

	public List<Article> getForPrintArticles() {
		return articleRepository.getForPrintArticles();
	}
	
}
