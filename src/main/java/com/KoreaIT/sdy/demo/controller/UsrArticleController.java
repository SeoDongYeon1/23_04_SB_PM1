package com.KoreaIT.sdy.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String showList(Model model) {
		List<Article> articles = articleService.getArticles();
		
		model.addAttribute("articles", articles);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, int id) {
		Article article =articleService.getArticleById(id);
		
		model.addAttribute(article);
		
		return "usr/article/detail";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData<Article> doWrite(HttpSession httpSession, String title, String body) {
		boolean isLogined = false;
		int loginedMemberId = -1;
		
		if(httpSession.getAttribute("loginedMemberId")!=null) {
			isLogined = true;
			loginedMemberId = (int)httpSession.getAttribute("loginedMemberId");
		}
		
		if(isLogined==false) {
			return ResultData.from("F-A", "로그인 상태가 아닙니다.");
		}
		
		if(Ut.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요.");
		}
		if(Ut.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요.");
		}
		
		ResultData<Integer> writeArticleRd = articleService.writeArticle(title, body, loginedMemberId);
		
		Article article = articleService.getArticleById(writeArticleRd.getData1());
		
		return ResultData.newData(writeArticleRd, article);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData<Article> doDelete(HttpSession httpSession, int id) {
		boolean isLogined = false;
		int loginedMemberId = -1;
		
		if(httpSession.getAttribute("loginedMemberId")!=null) {
			isLogined = true;
			loginedMemberId = (int)httpSession.getAttribute("loginedMemberId");
		}
		
		if(isLogined==false) {
			return ResultData.from("F-A", "로그인 상태가 아닙니다.");
		}
		
		Article article = articleService.getArticleById(id);
		
		if(article==null) {
			return ResultData.from("F-1", Ut.f("%d번 글은 존재하지 않습니다.", id));
		}
		
		if(article.getMemberId()!=loginedMemberId) {
			return ResultData.from("F-2", "해당 게시글에 권한이 없습니다.");
		}
		
		articleService.deleteArticle(id);
		return ResultData.from("S-1", Ut.f("%d번 글이 삭제되었습니다.", id));
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify(HttpSession httpSession, int id, String title, String body) {
		boolean isLogined = false;
		int loginedMemberId = -1;
		
		if(httpSession.getAttribute("loginedMemberId")!=null) {
			isLogined = true;
			loginedMemberId = (int)httpSession.getAttribute("loginedMemberId");
		}
		
		if(isLogined==false) {
			return ResultData.from("F-A", "로그인 상태가 아닙니다.");
		}
		
		Article article = articleService.getArticleById(id);
		
		if(article==null) {
			return ResultData.from("F-1", Ut.f("%d번 글은 존재하지 않습니다.", id));
		}
		
		if(article.getMemberId()!=loginedMemberId) {
			return ResultData.from("F-2", "해당 게시글에 권한이 없습니다.");
		}
		
		ResultData<Article> actorCanModifyRd = articleService.actorCanModifyRd(id, title, body);
		
		if(actorCanModifyRd.isFail()) {
			return actorCanModifyRd;
		}
		return ResultData.newData(actorCanModifyRd, article);
	}
	
}
