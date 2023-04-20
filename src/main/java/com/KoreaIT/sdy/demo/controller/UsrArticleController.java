package com.KoreaIT.sdy.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.sdy.demo.service.ArticleService;
import com.KoreaIT.sdy.demo.util.Ut;
import com.KoreaIT.sdy.demo.vo.Article;
import com.KoreaIT.sdy.demo.vo.ResultData;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public ResultData<List<Article>> showList() {
		List<Article> articles = articleService.getArticles();
		
		return ResultData.from("S-1", "Article List", articles);
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData<?> doWrite(String title, String body) {
		if(Ut.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요.");
		}
		if(Ut.empty(body)) {
			return ResultData.from("F-1", "내용을 입력해주세요.");
		}
		
		int id = articleService.writeArticle(title, body);
		
		Article article = articleService.getArticleById(id);
		
		return ResultData.from("S-1", Ut.f("%d번 글이 생성되었습니다.", id), article);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public Object doDelete(int id) {
		Article article = articleService.getArticleById(id);
		
		if(article==null) {
			return Ut.f("%d번 글은 존재하지 않습니다.", id);
		}
		articleService.deleteArticle(id);
		return Ut.f("%d번 글이 삭제되었습니다.", id);
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Object doModify(int id, String title, String body) {
		Article article = articleService.getArticleById(id);
		
		if(article==null) {
			return Ut.f("%d번 글은 존재하지 않습니다.", id);
		}
		article = articleService.modifyArticle(id, title, body);
		return Ut.f("%d번 글이 수정되었습니다.", id) + article;
	}
	
}
