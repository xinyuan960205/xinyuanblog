package cn.xinyuan.blog.admin.article.controller;

import cn.xinyuan.blog.admin.article.service.IBlogArticleInfoService;
import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.common.validator.ValidatorUtils;
import cn.xinyuan.blog.entity.article.DO.BlogArticleInfo;
import cn.xinyuan.blog.entity.article.DTO.ArticleContentDTO;
import cn.xinyuan.blog.entity.article.DTO.ArticleDTO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ArticleController
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/2/9 17:01
 */
@RestController
@RequestMapping("/admin/article")
public class ArticleController {

    @Autowired
    private IBlogArticleInfoService iBlogArticleInfoService;

    @GetMapping("/list")
    @RequiresPermissions("article:list")
    public Result listArticle(@RequestParam Map<String, Object> params) {
        PageUtils page = iBlogArticleInfoService.queryPage(params);
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        return Result.success(map);
    }

    @GetMapping("/info/{articleId}")
    @RequiresPermissions("article:list")
    public Result info(@PathVariable Long articleId) {
        ArticleContentDTO article = iBlogArticleInfoService.getArticle(articleId);
        return Result.success(article);
    }

    @PostMapping("/save")
    @RequiresPermissions("article:save")
    public Result saveArticle(@RequestBody ArticleContentDTO article){
        iBlogArticleInfoService.saveArticle(article);
        return Result.success();
    }

    @DeleteMapping("/delete")
    @RequiresPermissions("article:delete")
    @Transactional(rollbackFor = Exception.class)
    public Result deleteArticleBatch(@RequestBody Long[] articleIds){
        iBlogArticleInfoService.deleteArticlePatch(articleIds);
        return Result.success();
    }

    @PutMapping("/update")
    public Result updateArticle(@RequestBody ArticleContentDTO article){
        ValidatorUtils.validateEntity(article);
        iBlogArticleInfoService.updateArticle(article);
        return Result.success();
    }

    @PutMapping("/update/status")
    public Result updateStatus(@RequestBody BlogArticleInfo article) {
        iBlogArticleInfoService.updateById(article);
        return Result.success();
    }
}
