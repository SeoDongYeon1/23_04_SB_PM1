package com.KoreaIT.sdy.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.sdy.demo.service.ArticleService;
import com.KoreaIT.sdy.demo.util.Ut;
import com.KoreaIT.sdy.demo.vo.Article;
import com.KoreaIT.sdy.demo.vo.ResultData;
import com.KoreaIT.sdy.demo.vo.Rq;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/usr/article/list")
	public String showList(Model model) {
		List<Article> articles = articleService.getForPrintArticles();
		
		model.addAttribute("articles", articles);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Model model, int id) {
		Rq rq = new Rq(req);
		int loginedMemberId = rq.getLoginedMemberId();
		
		Article article =articleService.getForPrintArticle(loginedMemberId, id);
		
		model.addAttribute("article", article);
		model.addAttribute("loginedMemberId", loginedMemberId);
		
		return "usr/article/detail";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData<Article> doWrite(HttpServletRequest req, String title, String body) {
		Rq rq = new Rq(req);
		
		if(rq.isLogined()==false) {
			return ResultData.from("F-A", "로그인 상태가 아닙니다.");
		}
		
		if(Ut.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요.");
		}
		if(Ut.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요.");
		}
		
		ResultData<Integer> writeArticleRd = articleService.writeArticle(title, body, rq.getLoginedMemberId());
		
		Article article = articleService.getArticleById(writeArticleRd.getData1());
		
		return ResultData.newData(writeArticleRd, article);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {
		Rq rq = new Rq(req);
		if(rq.isLogined()==false) {
			return Ut.jsHistoryBack("F-A", "로그인 상태가 아닙니다.");
		}
		
		Article article = articleService.getArticleById(id);
		
		if(article==null) {
			return Ut.jsHistoryBack("F-1", Ut.f("%d번 글은 존재하지 않습니다.", id));
		}
		
		if(article.getMemberId()!=rq.getLoginedMemberId()) {
			return Ut.jsHistoryBack("F-2", "해당 게시글에 권한이 없습니다.");
		}
		
		articleService.deleteArticle(id);
		
		return Ut.jsReplace(Ut.f("%d번 글이 삭제되었습니다.", id), "../article/list");
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify(HttpServletRequest req, int id, String title, String body) {
		Rq rq = new Rq(req);
		
		if(rq.isLogined()==false) {
			return ResultData.from("F-A", "로그인 상태가 아닙니다.");
		}
		
		Article article = articleService.getArticleById(id);
		
		if(article==null) {
			return ResultData.from("F-1", Ut.f("%d번 글은 존재하지 않습니다.", id));
		}
		
		if(article.getMemberId()!=rq.getLoginedMemberId()) {
			return ResultData.from("F-2", "해당 게시글에 권한이 없습니다.");
		}
		
		ResultData<Article> actorCanModifyRd = articleService.actorCanModifyRd(id, title, body);
		
		if(actorCanModifyRd.isFail()) {
			return actorCanModifyRd;
		}
		return ResultData.newData(actorCanModifyRd, article);
	}
	
}
