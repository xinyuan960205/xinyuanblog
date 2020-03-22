package cn.xinyuan.blog.portal.article.controller;

import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.entity.article.VO.ArticleVO;
import cn.xinyuan.blog.portal.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName: ArticleController
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/2/10 17:35
 */
@RestController("articlePortalController")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles")
    public Result getArticleList(@RequestParam Map<String, Object> params) {
        PageUtils page = articleService.getArticleList(params);
        return Result.success(page);
    }

    @GetMapping("/article/{articleId}")
    public Result getArticleById(@PathVariable("articleId") Long articleId) {
        ArticleVO articleVO = articleService.getArticleById(articleId);
        if(articleVO==null){
            return Result.failed();
        }
        return Result.success(articleVO);
    }
}
